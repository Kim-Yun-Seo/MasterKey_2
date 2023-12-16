package com.cookandroid.masterkey

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class Bus5_mission : AppCompatActivity() {
    private lateinit var linearLayout2: LinearLayout
    private lateinit var cancel: Button
    private lateinit var adult: Button
    private lateinit var junior: Button
    private lateinit var bohoon: Button
    private lateinit var totalpay: TextView

    // Prices for different passenger types
    private val ADULT_PRICE = 10500
    private val JUNIOR_PRICE = 8500
    private val BOHOON_PRICE = 6500

    // Track selected seats
    private val selectedSeats = mutableListOf<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus5_mission)

        val btnLeftImageView = findViewById<ImageView>(R.id.btnLeft)
        val btnRightImageView = findViewById<ImageView>(R.id.btnRight)

        // Set OnClickListener for the left button
        btnLeftImageView.setOnClickListener {
            val intent = Intent(this, Bus4_mission::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for the right button
        btnRightImageView.setOnClickListener {
            // Handle the right button click
            val intent = Intent(this, MainActivity_playmode::class.java)
            startActivity(intent)
        }//Tool


        val nextButton: Button = findViewById(R.id.nextbus)
        val backButton: Button = findViewById(R.id.backbus)
        val homeButton: ImageView = findViewById(R.id.home)

        nextButton.setOnClickListener {
            // Start Bus6 activity
            val intent = Intent(this@Bus5_mission, Bus6_mission::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            // Start Bus4 activity
            val intent = Intent(this@Bus5_mission, Bus4_mission::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            // Start MainActivity
            val intent = Intent(this@Bus5_mission, Bus1_mission::class.java)
            startActivity(intent)
        }

        // Initialize views
        linearLayout2 = findViewById(R.id.linearLayout2)
        cancel = findViewById(R.id.cancel)
        adult = findViewById(R.id.adult)
        junior = findViewById(R.id.junior)
        bohoon = findViewById(R.id.bohoon)
        totalpay = findViewById(R.id.totalpay)

        // Set up seat click listeners
        val seatIds = listOf(
            R.id.seat1, R.id.seat2, R.id.seat3,
            R.id.seat4, R.id.seat5, R.id.seat6,
            R.id.seat7, R.id.seat8,
        )

        for (seatId in seatIds) {
            findViewById<ImageView>(seatId).setOnClickListener {
                handleSeatClick(it as ImageView)
            }
        }

        // Set up cancel button click listener
        cancel.setOnClickListener {
            linearLayout2.visibility = View.GONE
            clearSelectedSeats()
            updateTotalPay()
        }

        // Set up type selection listeners
        adult.setOnClickListener {
            linearLayout2.visibility = View.GONE
            updateTotalPay()
            changeSeatColor(Color.YELLOW)
        }

        junior.setOnClickListener {
            linearLayout2.visibility = View.GONE
            updateTotalPay()
            changeSeatColor(Color.YELLOW)
        }

        bohoon.setOnClickListener {var dlg= AlertDialog.Builder(this@Bus5_mission)
            dlg.setTitle("다시 선택해주세요!")
            dlg.setMessage("어른 1명, 어린이 1명 티켓을 예매해야합니다.")
            dlg.setIcon(R.drawable.error_icon)
            dlg.setPositiveButton("확인",null)
            dlg.show()
        }
    }

    private fun handleSeatClick(seat: ImageView) {
        // Toggle seat selection
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat)
        } else {
            selectedSeats.add(seat)
        }

        // Update UI based on seat selection
        linearLayout2.visibility = if (selectedSeats.isNotEmpty()) View.VISIBLE else View.GONE

        // Update total pay based on selected seats and passenger type
        updateTotalPay()

        // Check if the 3rd seat is clicked and required seats are selected
        if (seat.id == R.id.seat3 && selectedSeats.size >= 2) {
            // Hide linearLayout2
            linearLayout2.visibility = View.GONE
            showConfirmationDialog()
        }

    }
    private fun showConfirmationDialog() {
        val dlg = AlertDialog.Builder(this@Bus5_mission)
        dlg.setTitle("결제하기를 눌러주세요!")
        dlg.setMessage("어른 1명, 어린이 1명의 예매가 끝났습니다.")
        dlg.setIcon(R.drawable.error_icon)
        dlg.setPositiveButton("확인", null)
        dlg.show()
        totalpay.text = "총 금액: 17,000 원"
    }


    private fun clearSelectedSeats() {
        selectedSeats.forEach { seat ->
            // Reset seat color to the default color (you may need to define the default color)
            seat.setColorFilter(null)
        }
        selectedSeats.clear()
    }

    private fun updateTotalPay() {
        // 선택된 좌석과 승객 유형에 기반한 총 금액 계산
        var totalPrice = 0

        // 각 좌석에 대한 고정 가격을 사용하여 총 금액 계산
        for (seat in selectedSeats) {
            totalPrice += getPriceForPassengerType(seat)
        }

        // totalpay TextView 업데이트
        totalpay.text = "총 금액: $totalPrice 원"
    }

    // 선택된 승객 유형에 따라 가격을 반환하는 함수
    private fun getPriceForPassengerType(seat: ImageView): Int {
        return when {
            selectedSeats.isNotEmpty() -> { // 하나 이상의 좌석이 선택된 경우에만 가격을 반환
                if (adult.isPressed) ADULT_PRICE
                else if (junior.isPressed) JUNIOR_PRICE
                else if (bohoon.isPressed) BOHOON_PRICE
                else 0 // 선택된 승객 유형이 없을 경우
            }
            else -> 0// 좌석이 선택되지 않은 경우
        }
    }

    private fun changeSeatColor(color: Int) {
        for (seat in selectedSeats) {
            seat.setColorFilter(color)
        }
    }
}