package com.example.android_demo_sensor;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {// 0. 实现（implements）传感器事件监听器

	private SensorManager manager;
	private Sensor sensor;
	private TextView tv;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 2. 获取传感器管理者、
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// 4. 通过管理者对象得到本机中默认的传感器，
		sensor = manager.getDefaultSensor(Sensor.TYPE_ALL);

		// 1. 实例化文本框和按钮、
		tv = (TextView) findViewById(R.id.tv);
		btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 5. 然后在相应按钮点击时间后，在文本框逐行列举出本机中的传感器名字。
				List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);

				for (Sensor sensor : list) {

					tv.append("\n" + sensor.getName() + "\n");
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		// 3. 注册/反注册传感器管理者、
		manager.registerListener(this, sensor, SensorManager.SENSOR_ALL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 3. 注册/反注册传感器管理者、
		manager.unregisterListener(this, sensor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate(填充) the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

}
