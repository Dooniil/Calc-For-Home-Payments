package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.example.fragments.fragments.ElectricFragment
import com.example.fragments.fragments.WaterFragment
import com.example.fragments.fragments.ViewPagerAdapter
import com.example.fragments.fragments.GasFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ElectricFragment(), "Свет")
        adapter.addFragment(WaterFragment(), "Вода")
        adapter.addFragment(GasFragment(), "Газ")
        val view: ViewPager = findViewById(R.id.viewPager)
        view.adapter = adapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(view)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_wb_incandescent_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_waves_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_local_fire_department_24)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Подтверждение")
            setMessage("Вы уверены, что хотите выйти из программы?")

            setPositiveButton("Выйти") { _, _ ->
                super.onBackPressed()
            }
            setNegativeButton("Остаться"){_, _ ->
            }
            setCancelable(true)
        }.create().show()
    }
}