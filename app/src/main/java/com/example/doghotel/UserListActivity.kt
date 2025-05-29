package com.example.doghotel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val fileUtil = FileUtil()
        fileUtil.copyFileFromAssets(this, "users.txt")
        val userList = fileUtil.loadDogUsersFromFile(this)

        val recyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = DogUserAdapter(userList)
        recyclerView.adapter = adapter

        val confirmButton = findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            val selectedUser = adapter.getSelectedUser()
            if (selectedUser != null) {
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra("selectedUser", selectedUser) // ✅ 객체 통째로 전달
                startActivity(intent)
            }
        }
    }
}
