package com.example.sensors1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


public class MainActivity : AppCompatActivity() {

    lateinit var sensorManagerLight: SensorManager
    lateinit var sensorManagerGyroscope: SensorManager

    lateinit var sLight : TextView
    private lateinit var sGyroscope : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getGPSBtn =  findViewById<Button>(R.id.getLocation);

        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)

        getGPSBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val g = GPSTracker(applicationContext)
                val l = g.getLocation()
                if (l != null) {
                    val lat = l.latitude
                    val longi = l.longitude
                    Toast.makeText(
                        applicationContext, "LAT: "  + lat + "\n" + "LONG: " +
                                longi, Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        sLight  = findViewById<TextView>(R.id.light)
        sGyroscope = findViewById<TextView>(R.id.gyroscope)

        sensorManagerLight = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManagerLight.registerListener(
                luxSensor(sLight),
                sensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI
        )

        sensorManagerGyroscope = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManagerGyroscope.registerListener(
                gyroscopeSensor(sGyroscope),
                sensorManagerGyroscope.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI
        )

    }


    class luxSensor(var sLight : TextView) : SensorEventListener {
        public override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        public override fun onSensorChanged(event: SensorEvent?) {
            sLight.text = "luminosity: ${event?.values?.get(0)}"
        }

    }

    class gyroscopeSensor(var sGyroscope : TextView) : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent?) {
            sGyroscope.text = "Gyroscopicity: ${event?.values?.get(0)}"
        }

    }

    class GPSTracker : LocationListener {
        private lateinit var context : Context

        constructor( c: Context)
        {
            context = c
        }

        fun getLocation(): Location? {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show()
                return null
            }
            val lm =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            if (isGPSEnabled) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10f, this)
                return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            } else {
                Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show()
            }
            return null
        }

        override fun onLocationChanged(location: Location?) {
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

    }
}