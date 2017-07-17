package com.example.lenovo.congyunlong20170717;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{
    private int mnum;
    private double jiage ;
    private XListView xlv;
    private List<Data> list;
    private Button jiesuan;
    private CheckBox quanxuan;
    private Lvadapter lvadapter;
    private TextView heji;
    private int listnum = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

    }

    private void initData() {
        inData();
        xlv = (XListView) findViewById(R.id.xlv);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(this);
        jiesuan = (Button) findViewById(R.id.jiesuan);
        quanxuan = (CheckBox) findViewById(R.id.quanxuan);
        heji = (TextView) findViewById(R.id.jiage);
        lvadapter = new Lvadapter();
        xlv.setAdapter(lvadapter);
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quanxuan.isChecked()){
                       for (Data data : list) {
                            data.setFalg(true);
                           mnum +=data.getDnum();
                       jiesuan.setText("结算("+mnum+")");
                           jiage +=data.getJcar();
                        heji.setText("合计:"+jiage);
                       }
                       lvadapter.notifyDataSetChanged();
                }else{
                    for (Data data : list) {
                        data.setFalg(false);
                        mnum =0;
                        jiesuan.setText("结算("+mnum+")");
                        jiage =0;
                        heji.setText("合计:"+jiage);
                    }
                    lvadapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void inData() {
        list = new ArrayList<>();
        for (int i = 0; i <listnum; i++) {
            Data data = new Data("商品"+i,1,false,70.00+i);
            list.add(data);
                }

    }

    //上拉就刷新
    @Override
    public void onRefresh() {
        listnum +=10;
        inData();
        lvadapter.notifyDataSetChanged();
        onStopXlistView();
    }

    //下拉加载
    @Override
    public void onLoadMore() {
        listnum +=10;
        inData();
        lvadapter.notifyDataSetChanged();
        onStopXlistView();
    }

    public void onStopXlistView(){
        xlv.stopRefresh();
        xlv.stopLoadMore();

    }
    class Lvadapter extends BaseAdapter {

        @Override
        public int getCount() {

            return 10;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder vh;
            if (convertView==null){
                vh=new ViewHolder();
                convertView=convertView.inflate(MainActivity.this,R.layout.lv,null);
                vh.lvch= (CheckBox) convertView.findViewById(R.id.lvch);
                vh.tv1= (TextView) convertView.findViewById(R.id.lvname);
                vh.tv2= (TextView) convertView.findViewById(R.id.lvjiage);
               vh.addview = (AddView) convertView.findViewById(R.id.lvaddv);
                convertView.setTag(vh);
            }else{
                vh= (ViewHolder) convertView.getTag();
            }
            vh.tv1.setText(list.get(position).getName());
            vh.tv2.setText(list.get(position).getJcar()+"");
            vh.lvch.setChecked(list.get(position).isFalg());
            vh.addview.setNum(list.get(position).getDnum());
            vh.lvch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vh.lvch.isChecked()){
                        list.get(position).setFalg(true);
                        mnum +=list.get(position).getDnum();
                        jiage +=list.get(position).getJcar();
                        jiesuan.setText("结算("+mnum+")");
                        heji.setText("合计:"+jiage);

                    }else{
                        list.get(position).setFalg(false);
                        quanxuan.setChecked(false);
                        mnum -=list.get(position).getDnum();
                        jiage -=list.get(position).getJcar();
                        jiesuan.setText("结算("+mnum+")");
                        heji.setText("合计:"+jiage);
                    }
                }
            });
            if (list.get(position).isFalg()){
                vh.lvch.setChecked(true);
            }
            vh.addview.setLink(new AddViewLink() {
                @Override
                public void OncLinK(int num) {

                }
            });
            return convertView;
        }
    }
    class ViewHolder{
        AddView addview;
        CheckBox lvch;
        TextView tv1,tv2;
    }
}
