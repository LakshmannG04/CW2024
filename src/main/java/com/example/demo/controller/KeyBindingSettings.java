package com.example.demo.controller;

/**
 * The KeyBindingSettings class manages the key binding configuration for the game.
 * It allows the selection and retrieval of the current key binding, which can be either 
 * Arrow Keys or WASD Keys.
 */
public class KeyBindingSettings {

    /**
     * Enum representing the available key binding types for the game.
     */
    public enum Binding {
        ARROW_KEYS,  // Arrow keys for movement
        WASD_KEYS    // WASD keys for movement
    }

    // Default key binding (Arrow Keys)
    private static Binding currentBinding = Binding.ARROW_KEYS;

    /**
     * Retrieves the current key binding setting.
     *
     * @return The current key binding, either ARROW_KEYS or WASD_KEYS.
     */
    public static Binding getKeyBinding() {
        return currentBinding;
    }

    /**
     * Sets the key binding to a new value.
     *
     * @param binding The new key binding to set. It can either be ARROW_KEYS or WASD_KEYS.
     */
    public static void setKeyBinding(Binding binding) {
        currentBinding = binding;
    }
}
