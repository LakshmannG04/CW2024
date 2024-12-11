package com.example.demo.controller;

public class KeyBindingSettings {

    // Enum for defining the key binding types
    public enum Binding {
        ARROW_KEYS,
        WASD_KEYS
    }

    // Default key binding (Arrow Keys)
    private static Binding currentBinding = Binding.ARROW_KEYS;

    // Get the current key binding
    public static Binding getKeyBinding() {
        return currentBinding;
    }

    // Set the key binding
    public static void setKeyBinding(Binding binding) {
        currentBinding = binding;
    }
}
