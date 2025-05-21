package com.example.doghotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReviewFragment : Fragment() {

    private lateinit var reviewAdapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_review, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.reviewRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 더미 리뷰 데이터
        val dummyReviews = listOf(
            Review("홍길동", 4.5f, "정말 친절했어요!", isReported = false),
            Review("김댕댕", 3.0f, "보통이에요~", isReported = false),
            Review("유신장군", 5.0f, "완전 만족! 강추입니다!", isReported = true)
        )

        reviewAdapter = ReviewAdapter(dummyReviews)
        recyclerView.adapter = reviewAdapter

        return view
    }
}
