package com.example.numberpickerview.widget;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.example.numberpickerview.R;
import com.example.numberpickerview.interfaces.ValueChooseListener;

/**
 * Created by Administrator on 2016/10/9.
 */

public class NumberPickerView extends PopupWindow {
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
    );
    FrameLayout dialogplus_content_container;  // 遮罩层
    private ViewGroup decorView;//activity的根View
    private ViewGroup rootView;//附加View 的 根View

    private int gravity = Gravity.BOTTOM;
    private NumberPicker numberPicker;
    private Context context;

    private ImageView cancel_action;
    private ImageView confirm_action;

    public NumberPickerView(final Activity context) {
        this.context = context;
        initViews();
    }

    /**
     * 设置数字选择器最大值
     *
     * @param maxValue
     */
    public void setMaxValue(int maxValue) {
        numberPicker.setMaxValue(maxValue);
    }

    /**
     * 设置数字选择器最小值
     *
     * @param minValue
     */
    public void setMinValue(int minValue) {
        numberPicker.setMinValue(minValue);
    }

    protected void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decorView = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.add_popup_dialog, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        numberPicker = (NumberPicker) rootView.findViewById(R.id.numberPicker);
        dialogplus_content_container = (FrameLayout) rootView.findViewById(R.id.dialogplus_content_container);
        cancel_action = (ImageView) rootView.findViewById(R.id.cancel_action);
        confirm_action = (ImageView) rootView.findViewById(R.id.confirm_action);
        dialogplus_content_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zhuhu", "ssss");
                decorView.removeView(rootView);
            }
        });
    }

    /**
     * 显示popupWindow
     */
    public void showPopupWindow() {
        decorView.addView(rootView);
    }

    /**
     * 设置取消窗口listener
     */
    public void setOnCancelListener(final View.OnClickListener onCancelListener) {

        cancel_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelListener.onClick(v);
                decorView.removeView(rootView);
            }
        });

    }

    /**
     * 设置确定取值listener
     */
    public void setOnValueChooseListener(final ValueChooseListener listener) {
        confirm_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decorView.removeView(rootView);
                listener.onValueChoose(numberPicker.getValue());
            }
        });

    }
}
