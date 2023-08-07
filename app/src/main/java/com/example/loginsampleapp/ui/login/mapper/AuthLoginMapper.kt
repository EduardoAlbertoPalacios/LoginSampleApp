package com.example.loginsampleapp.ui.login.mapper

import com.baubap.domain.entities.AuthEntity

fun AuthEntity.toSuccessMessageLoginMapper() = "$name\n $message"
