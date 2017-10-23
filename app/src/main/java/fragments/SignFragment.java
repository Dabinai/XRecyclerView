package fragments;

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

import java.util.ArrayList;

import bean.SelectList;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sql.MySQL;

/**
 * Created by Dabin on 2017/10/21.
 */

public class SignFragment extends Fragment {
    View view;
    @Bind(R.id.sign_username)
    EditText signUsername;
    @Bind(R.id.sign_password)
    EditText signPassword;
    @Bind(R.id.sign_btn)
    Button signBtn;
    SQLiteDatabase writableDatabase;
    SQLiteDatabase readableDatabase;
    String username;
    String password;
    private ArrayList<SelectList> mDatas;
    int flag = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MySQL mySQL = new MySQL(getActivity());
        mDatas = new ArrayList<>();
        writableDatabase = mySQL.getWritableDatabase();
        readableDatabase = mySQL.getReadableDatabase();
       chaxu();
    }

    public void chaxu(){
        mDatas.clear();
        Cursor rs = readableDatabase.rawQuery("select * from jizhu", null);
        while (rs.moveToNext()) {
            String name = rs.getString(rs.getColumnIndex("name"));
            String pass = rs.getString(rs.getColumnIndex("pass"));
            mDatas.add(new SelectList(pass, name));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.sign_btn)
    public void onViewClicked() {
        username = signUsername.getText().toString();
        password = signPassword.getText().toString();
        if (!username.equals("") && !password.equals("")) {
            for (int i = 0; i < mDatas.size(); i++) {
                String s = mDatas.get(i).getName().toString();
                if(s.equals(username)){
                    Toast.makeText(getActivity(), "用户已存在哦！", Toast.LENGTH_SHORT).show();
                    flag = 1;
                    return;
                }
            }
            if(flag == 0){
                writableDatabase.execSQL("insert into jizhu(name,pass) values(?,?)", new String[]{username,password});
                Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                chaxu();
            }
        }else{
            Toast.makeText(getActivity(), "信息不完整", Toast.LENGTH_SHORT).show();
        }

    }
}
