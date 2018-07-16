package sg.edu.rp.c346.demofilereadwriting;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btnRead, btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        //folder creation
        final String folderLocation = getFilesDir().getAbsolutePath() + "/Folder";
        final File targetFile = new File(folderLocation, "data.txt");

        File folder = new File(folderLocation);
        if (folder.exists() == false) {
            boolean result = folder.mkdir();
            if (result) {
                Log.d("File Read/Write", "Folder created");
            }
            btnWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        FileWriter writer = new FileWriter(targetFile, true);
                        writer.write("Hello world \n");
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), "Failed to write", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            btnRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(targetFile.exists()){
                        try{
                            FileReader fileReader = new FileReader(targetFile);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            String result = "";
                            String line = bufferedReader.readLine();
                            while (line != null){
                                result += line + "\n";
                                line = bufferedReader.readLine();
                            }
                            bufferedReader.close();
                            fileReader.close();
                            textView.setText(result);
                        }catch (Exception e){
                            Toast.makeText(getBaseContext(), "Failed to read",Toast.LENGTH_SHORT);
                            e.printStackTrace();
                        }

                    }else{
                        Toast.makeText(getBaseContext(),"Cannot find the file",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}