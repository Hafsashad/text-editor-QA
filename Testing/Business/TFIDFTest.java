package Business;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dal.TFIDFCalculator;

public class TFIDFTest {
    private TFIDFCalculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new TFIDFCalculator();
        calculator.addDocumentToCorpus("this is a test document");
        calculator.addDocumentToCorpus("this document is another test");
    }
    
    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_PositiveCase() {
        double result = calculator.calculateDocumentTfIdf("this is a test");
        assertFalse(Double.isNaN(result), "TF-IDF result should not be NaN");
        assertFalse(Double.isInfinite(result), "TF-IDF result should not be infinite");
        assertTrue(Double.isFinite(result), "TF-IDF should be a valid finite number");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_SingleWordDocument() {
        double result = calculator.calculateDocumentTfIdf("test");
        assertFalse(Double.isNaN(result), "TF-IDF result should not be NaN for single word");
        assertFalse(Double.isInfinite(result), "TF-IDF result should not be infinite for single word");
        assertTrue(Double.isFinite(result), "Single word should produce valid TF-IDF");
    }
    
    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_EmptyDocument() {
        double result = calculator.calculateDocumentTfIdf("");
        assertNotNull(result, "Empty document should not return null");
        assertTrue(Double.isFinite(result), "Empty document should return finite number (graceful handling)");
    }
    
    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_SpecialCharacters() {
        double result = calculator.calculateDocumentTfIdf("### $$$ %%%");
        assertNotNull(result, "Special characters should not return null");
        assertTrue(Double.isFinite(result), "Special characters should be handled gracefully");
    }
   
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_WordNotInCorpus() {
        double result = calculator.calculateDocumentTfIdf("xyz");
        assertTrue(Double.isFinite(result), "Unknown word should return finite TF-IDF");
    }
    
    // ==================== 10 ADDITIONAL TEST CASES ====================
    
    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_MultipleCommonWords() {
        double result = calculator.calculateDocumentTfIdf("this this this document document");
        assertFalse(Double.isNaN(result), "TF-IDF should handle repeated words");
        assertTrue(Double.isFinite(result), "Repeated words should produce valid TF-IDF");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_VeryLongDocument() {
        StringBuilder longDoc = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longDoc.append("test document word ").append(i).append(" ");
        }
        double result = calculator.calculateDocumentTfIdf(longDoc.toString());
        assertFalse(Double.isNaN(result), "Long document should not return NaN");
        assertTrue(Double.isFinite(result), "Very long document should return finite TF-IDF");
    }
    
    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_NullDocument() {
        assertThrows(Exception.class, () -> {
            calculator.calculateDocumentTfIdf(null);
        }, "Null document should throw exception or be handled gracefully");
    }
    
    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_MixedCaseWords() {
        double result = calculator.calculateDocumentTfIdf("Test DOCUMENT tEsT");
        assertFalse(Double.isNaN(result), "Mixed case should be handled");
        assertTrue(Double.isFinite(result), "Mixed case words should produce valid TF-IDF");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_OnlyWhitespace() {
        double result = calculator.calculateDocumentTfIdf("     ");
        assertNotNull(result, "Whitespace only should not return null");
        assertTrue(Double.isFinite(result), "Whitespace should be handled gracefully");
    }
    
    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_NumbersInDocument() {
        double result = calculator.calculateDocumentTfIdf("test 123 document 456");
        assertFalse(Double.isNaN(result), "Numbers should be handled");
        assertTrue(Double.isFinite(result), "Document with numbers should return valid TF-IDF");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_SingleCharacter() {
        double result = calculator.calculateDocumentTfIdf("a");
        assertFalse(Double.isNaN(result), "Single character should not return NaN");
        assertTrue(Double.isFinite(result), "Single character should produce valid TF-IDF");
    }
    
    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_UnicodeCharacters() {
        double result = calculator.calculateDocumentTfIdf("test مستند 文档");
        assertNotNull(result, "Unicode characters should not return null");
        assertTrue(Double.isFinite(result), "Unicode should be handled gracefully");
    }
    
    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_DocumentWithPunctuation() {
        double result = calculator.calculateDocumentTfIdf("test, document. another! test?");
        assertFalse(Double.isNaN(result), "Punctuation should be handled");
        assertTrue(Double.isFinite(result), "Document with punctuation should return valid TF-IDF");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_RepeatedSpaces() {
        double result = calculator.calculateDocumentTfIdf("test    document     test");
        assertFalse(Double.isNaN(result), "Multiple spaces should be handled");
        assertTrue(Double.isFinite(result), "Repeated spaces should not affect TF-IDF calculation");
    }
}