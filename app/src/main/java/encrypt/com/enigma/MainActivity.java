package encrypt.com.enigma;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Button setR = (Button)findViewById(R.id.button);

    Rotor rotor = new Rotor();
    PlugBoard board = new PlugBoard();

    NumberPicker n1;
    NumberPicker n2;
    NumberPicker n3;
    NumberPicker n4;

    String[] s1 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] s2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] s3 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String[] s4 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    char k1,k2,k3,k4,k10,k11,k12,k13;
    String out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        n1 = (NumberPicker)findViewById(R.id.numberPicker1);
        n2 = (NumberPicker) findViewById(R.id.numberPicker2);
        n3 = (NumberPicker) findViewById(R.id.numberPicker3);
        n4 = (NumberPicker) findViewById(R.id.numberPicker4);

        n1.setMinValue(0);
        n1.setMaxValue(25);
        n1.setDisplayedValues(s1);
        /*n1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected value from picker

            }
        });*/

        n2.setMinValue(0);
        n2.setMaxValue(25);
        n2.setDisplayedValues(s2);

        n3.setMinValue(0);
        n3.setMaxValue(25);
        n3.setDisplayedValues(s3);

        n4.setMinValue(0);
        n4.setMaxValue(25);
        n4.setDisplayedValues(s4);
    }

    public void onClickGetRotor(View view)
    {
        k1=s1[n1.getValue()].charAt(0);
        k2=s2[n2.getValue()].charAt(0);
        k3=s3[n3.getValue()].charAt(0);
        k4=s4[n4.getValue()].charAt(0);
        k10=k1;k11=k2;k12=k3;k13=k4;
        TextView t = (TextView)findViewById(R.id.st);
        String g = "Rotor Position: "+Character.toString(k1)+Character.toString(k2)+Character.toString(k3)+Character.toString(k4);
        t.setText(g);
        rotor.setRotor(k1,k2,k3,k4);
        Toast.makeText(MainActivity.this, "Rotor Set",
                Toast.LENGTH_SHORT).show();
    }

    public void onClickGetMsg(View view)
    {
        EditText inp = (EditText)findViewById(R.id.msg);
        String msg = inp.getText().toString();
        out="";
        int count1=0,count2=0,count3=0,count4=0;

        for(int i=0;i<msg.length();i++)
        {
            if(msg.charAt(i)==32)
            {
                out=out+" ";
                continue;
            }
            char board1 = board.pbOutput(msg.charAt(i));
            char encrypt = rotor.encrypt(board1);
            char board2 = board.pbOutput(encrypt);
            out=out+Character.toString(board2);
            //System.out.printf("%c",board2);

            //set new rotor position
            count4++;
            k4=(char)(k4+1);
            if(k4>90)
                k4='A';
            if(count4==26)
            {
                count3++;
                count4=0;
                k3=(char)(k3+1);
                if(k3>90)
                    k3='A';
                if(count3==26)
                {
                    count2++;
                    count3=0;
                    k2=(char)(k2+1);
                    if(k2>90)
                        k2='A';
                    if(count2==26)
                    {
                        count1++;
                        count2=0;
                        k1=(char)(k1+1);
                        if(k1>90)
                            k1='A';
                        if(count1==26)
                            count1=0;
                    }
                }
            }
            rotor.setRotor(k1,k2,k3,k4);

        }
        TextView o=(TextView)findViewById(R.id.opt);
        o.setText(out);
        k1=s1[n1.getValue()].charAt(0);
        k2=s2[n2.getValue()].charAt(0);
        k3=s3[n3.getValue()].charAt(0);
        k4=s4[n4.getValue()].charAt(0);
        rotor.setRotor(k1,k2,k3,k4);
        /*TextView ct = (TextView)findViewById(R.id.ct);
        String g = "Current Rotor Position: "+Character.toString(k1)+Character.toString(k2)+Character.toString(k3)+Character.toString(k4);
        ct.setText(g);*/
    }

    public void onClickCopy(View view)
    {
        ClipboardManager clipboard = (ClipboardManager)   getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied", out);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Text Copied",
                Toast.LENGTH_SHORT).show();
    }
}
