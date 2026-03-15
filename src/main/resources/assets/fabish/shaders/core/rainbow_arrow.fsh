#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

in vec2 texCoord0;
in vec4 vertexColor;
out vec4 fragColor;

// Converts a hue [0-1] to an RGB color
vec3 hueToRGB(float h) {
    vec3 rgb = abs(mod(h * 6.0 + vec3(0.0, 4.0, 2.0), 6.0) - 3.0) - 1.0;
    return clamp(rgb, 0.0, 1.0);
}

void main() {
    vec4 tex = texture(Sampler0, texCoord0);
    if (tex.a < 0.1) discard;                    // respect transparency

    float hue = 0;        // 5.0 = cycle speed, tune freely
    vec3 rainbow = hueToRGB(hue);

    fragColor = vec4(rainbow * tex.rgb, tex.a) * vertexColor;
}