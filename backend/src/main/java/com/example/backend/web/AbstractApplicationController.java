package com.example.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.backend.util.ApplicationMapper;

public abstract class AbstractApplicationController {
    @Autowired
    ApplicationMapper mapper;
}
