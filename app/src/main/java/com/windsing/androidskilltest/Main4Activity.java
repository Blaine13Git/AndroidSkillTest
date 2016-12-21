package com.windsing.androidskilltest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * get contacts
 * the first step is to add permissions in AndroidManifest.xml
 * ScrollView
 */
public class Main4Activity extends AppCompatActivity {

    private TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView6 = (TextView) findViewById(R.id.textView6);
        fetchContacts();
    }

    //拉取联系人
    public void fetchContacts() {
        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NAME = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri phone_Content_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri email_CONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String email_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String data = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output = new StringBuffer();//创建一个缓冲区
        ContentResolver content_resolver = getContentResolver();//获取内容解析器

        Cursor cursor = content_resolver.query(CONTENT_URI, null, null, null, null);//查询返回游标

        //Loop for every contact in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NAME)));
                if (hasPhoneNumber > 0) {
                    output.append("\nFirst Name:" + name);
                    // Query and loop for every phone number of the contact
                    // 遍历这个联系人下的所有号码
                    Cursor phoneCursor = content_resolver.query(phone_Content_URI, null, phone_CONTACT_ID + "=?", new String[]{contact_id}, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(number));
                        output.append("\nPhone Number:" + phoneNumber);
                    }
                    phoneCursor.close();

                    // Query and loop for every email of the contact
                    Cursor emailCursor = content_resolver.query(email_CONTENT_URI, null, email_CONTACT_ID + "=?", new String[]{contact_id}, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(data));
                        output.append("\nemail:" + email);
                    }
                    emailCursor.close();
                }
                output.append("\n");
            }
            textView6.setText(output);
        }
    }
}
