package com.callittips.snishimura.buttontouchsampleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.button_touch)
    Button mTouchButton;

    @InjectView(R.id.button_reset)
    Button mResetButton;

    @InjectView(R.id.tv_x_value)
    TextView mXvalue;

    @InjectView(R.id.tv_y_value)
    TextView mYvalue;

    @InjectView(R.id.tv_event_value_touch)
    TextView mTouchValue;

    @InjectView(R.id.tv_event_value_click)
    TextView mClickValue;

    @InjectView(R.id.tv_event_value_longclick)
    TextView mLongclickValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
    }

    @OnTouch(R.id.button_touch)
    boolean onTouch(View v, MotionEvent event) {
        mXvalue.setText(Float.toString(event.getX()));
        mYvalue.setText(Float.toString(event.getY()));
        mTouchValue.setText(MotionEvent.actionToString(event.getAction()));

        // ACTION_CANCEL時のみToast表示
        if (event.getAction() == MotionEvent.ACTION_CANCEL) {
            Toast.makeText(getApplicationContext(),
                    MotionEvent.actionToString(event.getAction()), Toast.LENGTH_SHORT).show();
        }

        Log.d(TAG, "Button touched: " + MotionEvent.actionToString(event.getAction()));
        return true;
    }

    @OnClick({
            R.id.button_touch,
            R.id.button_reset
    })
    void onClick(Button b) {
        switch (b.getId()) {
            case R.id.button_touch:
                mClickValue.setText("Button Clicked");
                break;
            case R.id.button_reset:
                clearValues();
                break;
            default:
                break;
        }
        Log.d(TAG, "Button clicked");
    }

    @OnLongClick(R.id.button_touch)
    boolean onLongClick(Button b) {
        mLongclickValue.setText("Button Long Pressed");
        Log.d(TAG, "Button Long pressed");
        return false;
    }

    private void clearValues() {
        mXvalue.setText("");
        mYvalue.setText("");
        mTouchValue.setText("");
        mClickValue.setText("");
        mLongclickValue.setText("");
    }
}
