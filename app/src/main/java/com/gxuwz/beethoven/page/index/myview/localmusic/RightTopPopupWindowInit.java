package com.gxuwz.beethoven.page.index.myview.localmusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class RightTopPopupWindowInit {

    RightTopPopupWindow rightTopPopupWindow;

    public RightTopPopupWindowInit(WindowManager wm, LayoutInflater layoutInflater) {
        this.rightTopPopupWindow = new RightTopPopupWindow(wm,layoutInflater);
    }

    public void init(ImageView toMany){
        toMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightTopPopupWindow.showAtLocation(view);
            }
        });
    }
}
