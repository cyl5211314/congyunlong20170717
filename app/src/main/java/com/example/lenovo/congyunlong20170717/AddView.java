package com.example.lenovo.congyunlong20170717;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/7/17 9:45
 */

public class AddView extends LinearLayout implements View.OnClickListener {

    private int num = 0;

    private Button jia;
    private Button jian;
    private EditText added;

    private AddViewLink link;

    public void setLink(AddViewLink link) {
        this.link = link;
    }

    public int getNum() {

        return num;
    }

    public void setNum(int num) {
        this.num = num;
        added.setText(num+"");
    }

    public AddView(Context context) {
        this(context,null);
    }

    public AddView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context,R.layout.addview,this);

        jia = (Button) view.findViewById(R.id.addja);

        jian = (Button) view.findViewById(R.id.addjian);

        added = (EditText) view.findViewById(R.id.added);

        jian.setOnClickListener(this);
        jia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    switch(v.getId()){//View点击事件选择使用
        case R.id.addja:
            num++;
            added.setText(num+"");
            link.OncLinK(num);
        break;
        case R.id.addjian:
            num--;
            added.setText(num+"");
            link.OncLinK(num);
            break;
        default :
        break;
    }
    }
}
