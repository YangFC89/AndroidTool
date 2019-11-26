# 自定义控件
# 垂直滚动TextView

xml文件里:

    <com.yfc.androidu.widget.VerticalTextview
            android:id="@+id/text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
           />
activity里:

        TextView.setTextList(titleList);//加入显示内容,集合类型
        TextView.setText(26, 5, Color.RED);//设置属性,具体跟踪源码
        TextView.setTextStillTime(3000);//设置停留时长间隔
        TextView.setAnimTime(300);//设置进入和退出的时间间隔
        //对单条文字的点击监听
        TextView.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    // TO DO
                    }
                });

    //开始滚动
    @Override
        protected void onResume() {
            super.onResume();
            TextView.startAutoScroll();
        }
    //停止滚动
    @Override
        protected void onPause() {
            super.onPause();
            TextView.stopAutoScroll();
        }
