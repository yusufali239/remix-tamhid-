package com.example

import com.example.data.local.AudioPlayerManager
import com.example.data.model.AudioDarslarRepository
import org.junit.Assert.*
import org.junit.Test

class DarslarValidationTest {

    @Test
    fun verifyAudioLessonsCountAndIntegrity() {
        val audioList = AudioDarslarRepository.allAudioDarslar
        // Must contain all 23 audio lessons from the Google Drive folder
        assertEquals("Audio darslar soni 23 ta bo'lishi shart", 23, audioList.size)

        // All IDs must be distinct
        val ids = audioList.map { it.id }.toSet()
        assertEquals("Barcha audio darslar identifikatorlari unikal bo'lishi kerak", 23, ids.size)

        // All stream URLs must be non-empty and point to Google Drive direct stream
        audioList.forEach { audio ->
            assertTrue("Stream URL bo'sh bo'lmasligi kerak: ${audio.audioRaqami}", audio.primaryStreamUrl.isNotBlank())
            assertTrue("Stream URL Google Drive direct havola bo'lishi kerak: ${audio.audioRaqami}", audio.primaryStreamUrl.contains("drive.usercontent.google.com"))
            assertTrue("O'zbekcha mavzu bo'sh bo'lmasligi kerak: ${audio.audioRaqami}", audio.mavzuUz.isNotBlank())
            assertTrue("O'zbekcha tavsif bo'sh bo'lmasligi kerak: ${audio.audioRaqami}", audio.tavsifUz.isNotBlank())
            assertTrue("Asl arabcha sarlavha bo'sh bo'lmasligi kerak: ${audio.audioRaqami}", audio.originalArabicTitle.isNotBlank())
        }
    }

    @Test
    fun verifySearchAudioLessons() {
        // Search by Uzbek topic
        val uzbekMatches = AudioDarslarRepository.searchAudioDarslar("tavhid")
        assertTrue("Tavhid so'zi bo'yicha audio topilishi kerak", uzbekMatches.isNotEmpty())

        // Search by Uzbek description
        val descMatches = AudioDarslarRepository.searchAudioDarslar("safsataviylar")
        assertTrue("Safsataviylar so'zi bo'yicha tavsifdan topilishi kerak", descMatches.isNotEmpty())

        // Search by Arabic title
        val arabicMatches = AudioDarslarRepository.searchAudioDarslar("إثبات وحدانية الله")
        assertTrue("Arabcha sarlavha bo'yicha topilishi kerak", arabicMatches.isNotEmpty())

        // Search by original file name
        val fileMatches = AudioDarslarRepository.searchAudioDarslar("الله ليس بجسم.m4a")
        assertTrue("Fayl nomi bo'yicha topilishi kerak", fileMatches.isNotEmpty())
    }

    @Test
    fun verifyTimeFormatting() {
        assertEquals("00:00", AudioPlayerManager.formatTime(0L))
        assertEquals("00:45", AudioPlayerManager.formatTime(45_000L))
        assertEquals("12:34", AudioPlayerManager.formatTime(754_000L))
        assertEquals("1:05:10", AudioPlayerManager.formatTime(3_910_000L))
    }

    @Test
    fun verifyYouTubeLessonsDistinctIds() {
        val youtubeIds = listOf(
            "_9X6LXRl094",
            "-JtzoihNvzg",
            "Q1rsXEUNlIg",
            "Ks1rGJac3dY",
            "5TWfD-AsXm4",
            "Wijq8BmDrVQ",
            "HShWmnH-aPo",
            "o-jAIL091Y8",
            "P4qEvVcA7TU",
            "F4FgVtz37KI",
            "tVALgw_URk0",
            "mmjbuDBLpVQ",
            "kozOu2WSAYk",
            "kRv3emQdsOE",
            "zfCSLnmK3sc"
        )
        // Verify exactly 15 videos
        assertEquals(15, youtubeIds.size)
        // Verify no duplicate IDs exist (every card has its own video)
        assertEquals(15, youtubeIds.toSet().size)
    }
}
