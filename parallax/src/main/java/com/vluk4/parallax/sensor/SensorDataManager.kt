package com.vluk4.example.parallax.sensor

import android.content.Context
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.vluk4.example.parallax.model.SensorData
import kotlinx.coroutines.channels.Channel

internal class SensorDataManager(context: Context) : SensorEventListener {

    private val sensorManager by lazy { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    private val accelerometer by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) }
    private val magnetometer by lazy { sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) }
    private var geomagnetic: FloatArray? = null
    private var gravity: FloatArray? = null
    private var screenOrientation: Int = Configuration.ORIENTATION_PORTRAIT

    val data by lazy { Channel<SensorData>(Channel.UNLIMITED) }

    fun init(newScreenOrientation: Int) {
        screenOrientation = newScreenOrientation
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME)
    }

    fun cancel() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_GRAVITY -> gravity = it.values
                Sensor.TYPE_MAGNETIC_FIELD -> geomagnetic = it.values
            }

            if (gravity != null && geomagnetic != null) {
                processSensorData()
            }
        }
    }

    private fun processSensorData() {
        val r = FloatArray(9)
        val i = FloatArray(9)

        if (SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)) {
            val orientation = FloatArray(3)
            SensorManager.getOrientation(r, orientation)

            val (pitch, roll) = when (screenOrientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    Pair(orientation[1], orientation[2])
                }

                else -> {
                    Pair(orientation[2], -orientation[1])
                }
            }


            if (pitch in -1.5f..1.5f && roll in -2f..2f) { // Its not upside down
                data.trySend(SensorData(roll = roll, pitch = pitch))
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}

