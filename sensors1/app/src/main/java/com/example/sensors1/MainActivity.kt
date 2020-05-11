package com.example.sensors1

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sensorManagerLight: SensorManager
    lateinit var sensorManagerGyroscope: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var _light = findViewById<TextView>(R.id.light)
        var _gyroscope = findViewById<TextView>(R.id.gyroscope)

        sensorManagerLight = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManagerLight.registerListener(
                luxSensor(),
                sensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL
        )

        sensorManagerGyroscope = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManagerGyroscope.registerListener(
                gyroscopeSensor(),
                sensorManagerGyroscope.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL
        )
    }


    class luxSensor : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            _light.text = "value: ${event?.values?.get(0)}"
        }

    }

    class gyroscopeSensor : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            gyroscope.text = "value: ${event?.values?.get(0)}"
        }

    }
}