package com.example.cinemaxapp.core.model


data class CreditPerson(
    val id: String,

    val name: String,

    /**
     * The role label shown below the person's name in the UI.
     * Cast → character name (e.g. "Peter Parker / Spider-Man")
     * Crew → job title (e.g. "Director")
     */
    val role: String,

    val profileUrl: String,
)
