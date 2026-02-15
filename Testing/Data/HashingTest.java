package Data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import dal.HashCalculator;

class HashingTest {
    
    @Test
    @DisplayName("Hash verification for numeric content")
    void testHashWithNumericData() {
        try {
            // Arrange: Numeric string
            String numericContent = "1234567890";
            
            // Act
            String hash = HashCalculator.calculateHash(numericContent);
            
            // Assert
            assertNotNull(hash, "Hash should not be null for numeric content");
            assertEquals(32, hash.length(), "Hash length should be 32 for numeric data");
            assertTrue(hash.matches("[0-9A-F]+"), "Hash should be valid hexadecimal");
        } catch (Exception e) {
            fail("Exception during numeric hash calculation: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash stability across multiple calculations")
    void testMultipleHashCalculations() {
        try {
            // Arrange
            String testContent = "Stability test content";
            
            // Act: Calculate hash 5 times
            String hash1 = HashCalculator.calculateHash(testContent);
            String hash2 = HashCalculator.calculateHash(testContent);
            String hash3 = HashCalculator.calculateHash(testContent);
            String hash4 = HashCalculator.calculateHash(testContent);
            String hash5 = HashCalculator.calculateHash(testContent);
            
            // Assert: All should be identical
            assertEquals(hash1, hash2, "Hash 1 and 2 should match");
            assertEquals(hash2, hash3, "Hash 2 and 3 should match");
            assertEquals(hash3, hash4, "Hash 3 and 4 should match");
            assertEquals(hash4, hash5, "Hash 4 and 5 should match");
        } catch (Exception e) {
            fail("Exception during stability test: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash uniqueness for similar strings")
    void testHashUniquenessForSimilarStrings() {
        try {
            // Arrange: Very similar content
            String content1 = "Testing123";
            String content2 = "Testing124";
            String content3 = "Testing125";
            
            // Act
            String hash1 = HashCalculator.calculateHash(content1);
            String hash2 = HashCalculator.calculateHash(content2);
            String hash3 = HashCalculator.calculateHash(content3);
            
            // Assert: All should be different
            assertNotEquals(hash1, hash2, "Hash1 and Hash2 should differ");
            assertNotEquals(hash2, hash3, "Hash2 and Hash3 should differ");
            assertNotEquals(hash1, hash3, "Hash1 and Hash3 should differ");
        } catch (Exception e) {
            fail("Exception during uniqueness test: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Whitespace-only content hash validation")
    void testHashWithWhitespaceOnly() {
        // Arrange
        String whitespaceContent = "     \t\t\t     ";
        
        // Assert
        assertDoesNotThrow(() -> {
            String hash = HashCalculator.calculateHash(whitespaceContent);
            assertNotNull(hash, "Whitespace-only content should produce hash");
            assertEquals(32, hash.length(), "Whitespace hash should be 32 chars");
        }, "Whitespace content should be processed without error");
    }
    
    @Test
    @DisplayName("Hash calculation with line breaks and paragraphs")
    void testHashWithMultilineContent() {
        try {
            // Arrange
            String multilineContent = "First line\nSecond line\nThird line\nFourth line";
            
            // Act
            String hash = HashCalculator.calculateHash(multilineContent);
            
            // Assert
            assertNotNull(hash, "Multiline content should produce hash");
            assertEquals(32, hash.length(), "Hash length should be 32");
            assertTrue(hash.matches("[0-9A-F]+"), "Should contain only hex characters");
        } catch (Exception e) {
            fail("Exception with multiline content: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash validation for mixed alphanumeric content")
    void testHashWithMixedContent() {
        try {
            // Arrange
            String mixedContent = "ABC123xyz789!@#";
            
            // Act
            String hash = HashCalculator.calculateHash(mixedContent);
            
            // Assert
            assertNotNull(hash, "Mixed content should generate hash");
            assertFalse(hash.isEmpty(), "Hash should not be empty");
            assertEquals(32, hash.length(), "Hash should be exactly 32 characters");
        } catch (Exception e) {
            fail("Exception with mixed content: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Single character content hash test")
    void testHashWithSingleCharacter() {
        try {
            // Arrange
            String singleChar = "X";
            
            // Act
            String hash = HashCalculator.calculateHash(singleChar);
            
            // Assert
            assertNotNull(hash, "Single character should produce hash");
            assertEquals(32, hash.length(), "Single char hash should be 32 length");
            assertTrue(hash.matches("[0-9A-F]+"), "Hash format should be valid");
        } catch (Exception e) {
            fail("Exception with single character: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Repeated character pattern hash verification")
    void testHashWithRepeatedCharacters() {
        try {
            // Arrange
            String repeatedContent = "AAAAAAAAAA";
            
            // Act
            String hash = HashCalculator.calculateHash(repeatedContent);
            
            // Assert
            assertNotNull(hash, "Repeated characters should hash properly");
            assertEquals(32, hash.length(), "Hash length verification");
            assertFalse(hash.equals("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), 
                        "Hash should not be same as input pattern");
        } catch (Exception e) {
            fail("Exception with repeated characters: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash comparison between uppercase and lowercase")
    void testHashCaseSensitivity() {
        try {
            // Arrange
            String upperCase = "TESTSTRING";
            String lowerCase = "teststring";
            String mixedCase = "TestString";
            
            // Act
            String hashUpper = HashCalculator.calculateHash(upperCase);
            String hashLower = HashCalculator.calculateHash(lowerCase);
            String hashMixed = HashCalculator.calculateHash(mixedCase);
            
            // Assert: All should be different (case sensitive)
            assertNotEquals(hashUpper, hashLower, "Upper and lower case should differ");
            assertNotEquals(hashLower, hashMixed, "Lower and mixed case should differ");
            assertNotEquals(hashUpper, hashMixed, "Upper and mixed case should differ");
        } catch (Exception e) {
            fail("Exception during case sensitivity test: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash generation with punctuation marks")
    void testHashWithPunctuationOnly() {
        try {
            // Arrange
            String punctuation = ".,;:!?-()[]{}";
            
            // Act
            String hash = HashCalculator.calculateHash(punctuation);
            
            // Assert
            assertNotNull(hash, "Punctuation should generate valid hash");
            assertEquals(32, hash.length(), "Punctuation hash should be 32 chars");
            assertTrue(hash.matches("[0-9A-F]+"), "Hash should be hexadecimal");
        } catch (Exception e) {
            fail("Exception with punctuation: " + e.getMessage());
        }
    }
}