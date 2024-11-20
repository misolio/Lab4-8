package Toy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlushToyTest {

    @Test
    void calculateSize() {
        PlushToy plushToy = new PlushToy("Teddy Bear", "Plush", 50, 10, "Fabric", 3, "Cuddly Toy",
                "Bear", true, 30, 20, 10);
        assertEquals(6000, plushToy.calculateSize());
    }

    @Test
    void testToString() {
        PlushToy plushToy = new PlushToy("Teddy Bear", "Plush", 50, 10, "Fabric", 3, "Cuddly Toy",
                "Bear", true, 30, 20, 10);
        String expectedString = "'Teddy Bear', тип ='Plush', ціна=50, матеріал='Fabric', вікові обмеження=3, тип гри='Cuddly Toy'" +
                "вид='Bear', звукові ефекти=true, розмір=6000}";
        assertEquals(expectedString, plushToy.toString());
    }
}
