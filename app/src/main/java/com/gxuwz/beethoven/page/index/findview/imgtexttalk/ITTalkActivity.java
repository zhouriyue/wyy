package com.gxuwz.beethoven.page.index.findview.imgtexttalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.ittalk.ITTalkAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.talk.Comment;
import com.gxuwz.beethoven.model.entity.find.talk.ITTalk;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文说说 Image-text talk
 */
public class ITTalkActivity extends AppCompatActivity {

    Context context;
    RecyclerView ittalkRv;
    List<ITTalk> itTalkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_ittalk);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
    }

    ImageView textUser;

    public void findByIdAndNew(){
        context = ITTalkActivity.this;
        ittalkRv = findViewById(R.id.ittalk_rv);
        itTalkList = new ArrayList<ITTalk>();
        textUser = findViewById(R.id.text_user);
        setData();
        ittalkRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        ittalkRv.setAdapter(new ITTalkAdapter(context,itTalkList));
    }

    public void setData(){
        textUser = findViewById(R.id.text_user);
        textUser.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("youth",context)));
        ITTalk itTalk = new ITTalk();
        List<String> images = new ArrayList<String>();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
        itTalk.setImages(images);
        itTalk.setContent("杜特尔特表示：“只要俄罗斯和中国的疫苗和市场上其他疫苗一样好，我们将优先考虑这两个国家的疫苗。”他还提及，菲律宾购买的任何疫苗都必须经过招标程序 报道称，菲律宾已经与俄罗斯、中国，以及美国制药公司辉瑞和莫德纳(Moderna)等潜在的疫苗供应商谈判，也计划和澳大利亚生物技术巨头CSL集团接洽。杜特尔特13日还特别提到中国，他指出，不同于其他国家，中国不要求支付定金或预付款。");
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUser.setUserName("zhouryue");
        itTalk.setSysUser(sysUser);
        itTalk.setLikeNumber(200);
        itTalk.setMessNumber(200);
        itTalk.setShareNumber(200);
        itTalk.setTitle("「海棠花开时」，我们还会见面吗？");
        List<Comment> commentList = new ArrayList<Comment>();
        Comment comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        itTalk.setCommentList(commentList);
        itTalkList.add(itTalk);

        itTalk = new ITTalk();
        images = new ArrayList<String>();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
        itTalk.setImages(images);
        itTalk.setContent("杜特尔特表示：“只要俄罗斯和中国的疫苗和市场上其他疫苗一样好，我们将优先考虑这两个国家的疫苗。”他还提及，菲律宾购买的任何疫苗都必须经过招标程序。\n" +
                "\n" +
                "　　报道称，菲律宾已经与俄罗斯、中国，以及美国制药公司辉瑞和莫德纳(Moderna)等潜在的疫苗供应商谈判，也计划和澳大利亚生物技术巨头CSL集团接洽。\n" +
                "\n" +
                "　　杜特尔特13日还特别提到中国，他指出，不同于其他国家，中国不要求支付定金或预付款。\n");
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUser.setUserName("zhouryue");
        itTalk.setSysUser(sysUser);
        itTalk.setLikeNumber(200);
        itTalk.setMessNumber(200);
        itTalk.setShareNumber(200);
        itTalk.setTitle("「海棠花开时」，我们还会见面吗？");
        commentList = new ArrayList<Comment>();
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？「海棠花开时」，我们还会见面吗？「海棠花开时」，我们还会见面吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？「海棠花开时」，我们还会见面吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        comment = new Comment();
        comment.setContent("你好吗？");
        sysUser = new SysUser();
        sysUser.setUserName("周日月");
        sysUser.setPerPic("zhoushen1");
        comment.setSysUser(sysUser);
        commentList.add(comment);
        itTalk.setCommentList(commentList);
        itTalkList.add(itTalk);
    }
}
