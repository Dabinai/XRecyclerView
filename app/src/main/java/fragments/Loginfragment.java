package fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dabin.sanzhou.R;
import com.dabin.sanzhou.SongActivity;

import java.util.ArrayList;

import bean.SelectTwo;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sql.MySQL;

/**
 * Created by Dabin on 2017/10/21.
 */

public class Loginfragment extends Fragment {
    SQLiteDatabase writableDatabase;
    View view;
    @Bind(R.id.login_username)
    EditText loginUsername;
    @Bind(R.id.login_passwrod)
    EditText loginPasswrod;
    @Bind(R.id.login_btn)
    Button loginBtn;
    private ArrayList<SelectTwo> mDatas;
    int flag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas = new ArrayList<>();
        MySQL mySQL = new MySQL(getActivity());
        writableDatabase = mySQL.getWritableDatabase();
        chaxu();
    }
    public void chaxu(){
        Cursor rs = writableDatabase.rawQuery("select * from jizhu", null);
        while (rs.moveToNext()) {
            String name = rs.getString(rs.getColumnIndex("name"));
            String pass = rs.getString(rs.getColumnIndex("pass"));
            mDatas.add(new SelectTwo(name, pass));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked() {
        String username = loginUsername.getText().toString();
        String password = loginPasswrod.getText().toString();
        if (!username.equals("") && !password.equals("")) {
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).getName().toString().equals(username) && mDatas.get(i).getPass().toString().equals(password)) {
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), SongActivity.class));
                    flag = 1;
                    return;
                }
            }
            if (flag == 0) {
                Toast.makeText(getActivity(), "账号或密码不正确", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "信息不完整", Toast.LENGTH_SHORT).show();
        }
    }
}
