package Toy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EducationalToyTest {

    @Test
    void calculateSize() {
        EducationalToy eduToy = new EducationalToy("Lego Set", "Educational", 100, 20, "Plastic", 6, "Building Toy",
                "Construction", 50);
        assertEquals(500, eduToy.calculateSize());
    }

    @Test
    void testToString() {
        EducationalToy eduToy = new EducationalToy("Lego Set", "Educational", 100, 20, "Plastic", 6, "Building Toy",
                "Construction", 50);
        String expectedString = "'Lego Set', тип ='Educational', ціна=100, матеріал='Plastic', вікові обмеження=6, тип гри='Building Toy'" +
                "вид='Construction', кількість деталей=50, розмір=500}";
        assertEquals(expectedString, eduToy.toString());
    }
}
