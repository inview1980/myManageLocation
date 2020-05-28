package my_manage.password_box.page;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my_manage.adapter_page.PwdPageAdapter;
import my_manage.password_box.R;
import my_manage.password_box.pojo.UserItem;
import my_manage.tool.database.DbHelper;


public final class PasswordManageViewPagerHome extends AppCompatActivity  {
    @BindView(R.id.tab_title) TabLayout tabTitle;
    @BindView(R.id.toolbar)   Toolbar   toolbar;
    @BindView(R.id.viewPage)  ViewPager viewPage;

    private List<UserItem> userLst;
    private int passwordTypeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_toolbar_tablayout_viewpage);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        initPage();
    }

    private void initPage() {
        Bundle bundle      = getIntent().getExtras();
        int    currentItem = bundle.getInt("currentItem", 0);
        passwordTypeId=bundle.getInt("PasswordTypeId",0);
        String title=bundle.getString("title");
        Log.i(this.getLocalClassName(), "currentItem:" + currentItem);
        if (-1 == currentItem) {
            //==-1时，为新增
            viewPage.setAdapter(new PwdPageAdapter(getSupportFragmentManager(), null,title));
            viewPage.setCurrentItem(0);
            tabTitle.addTab(tabTitle.newTab().setText(R.string.new_cn));
        } else {
            userLst = DbHelper.getInstance().getItemsByAfter(this,passwordTypeId);
            viewPage.setAdapter(new PwdPageAdapter(getSupportFragmentManager(), userLst,title));
            viewPage.setCurrentItem(currentItem);
            initTabLayout();
            tabTitle.getTabAt(currentItem).select();
            viewPage.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    tabTitle.getTabAt(position).select();
                }
            });
        }
    }

    private void initTabLayout() {
        for (final UserItem item : userLst) {
            tabTitle.addTab(tabTitle.newTab().setText(item.getItemName()));
        }
        tabTitle.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPage){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                viewPage.setCurrentItem(tab.getPosition());
            }
        });
    }
}
