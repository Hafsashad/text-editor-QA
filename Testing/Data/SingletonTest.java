package Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import dal.DatabaseConnection;

class SingletonTest {
   
    @Test
    @DisplayName("Verify singleton consistency across different method calls")
    void testSingletonConsistencyAcrossMethods() {
        // Act: Get instances in different contexts
        DatabaseConnection firstInstance = DatabaseConnection.getInstance();
        DatabaseConnection secondInstance = retrieveInstanceFromAnotherMethod();
       
        // Assert: Should be identical
        assertSame(firstInstance, secondInstance,
                   "Singleton should maintain same instance across different method calls");
    }
    
    private DatabaseConnection retrieveInstanceFromAnotherMethod() {
        return DatabaseConnection.getInstance();
    }
   
    @Test
    @DisplayName("Singleton instance reference should remain stable")
    void testSingletonReferenceStability() {
        // Act: Store reference and retrieve again
        DatabaseConnection original = DatabaseConnection.getInstance();
        DatabaseConnection retrieved = DatabaseConnection.getInstance();
       
        // Assert: References should point to same object
        assertTrue(original == retrieved,
                   "Reference equality should hold for singleton");
    }
   
    @Test
    @DisplayName("Verify singleton behavior with sequential access")
    void testSequentialAccessToSingleton() {
        // Act: Access singleton sequentially 5 times
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        DatabaseConnection db3 = DatabaseConnection.getInstance();
        DatabaseConnection db4 = DatabaseConnection.getInstance();
        DatabaseConnection db5 = DatabaseConnection.getInstance();
       
        // Assert: All should reference the same object
        assertSame(db1, db2, "Instance 1 and 2 should be identical");
        assertSame(db3, db4, "Instance 3 and 4 should be identical");
        assertSame(db5, db1, "Instance 5 and 1 should be identical");
    }
   
    @Test
    @DisplayName("Singleton should be instantiated only once")
    void testSingleInstantiation() {
        // Act: Get instance twice
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        DatabaseConnection connection2 = DatabaseConnection.getInstance();
       
        // Assert: Should be the exact same object
        assertEquals(connection1, connection2,
                     "Singleton should only be instantiated once");
        assertSame(connection1, connection2,
                   "Both variables should point to same singleton instance");
    }
   
    @Test
    @DisplayName("Check singleton instance is never null on repeated calls")
    void testRepeatedCallsNeverReturnNull() {
        // Act & Assert: Multiple calls should never return null
        for (int i = 0; i < 10; i++) {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            assertNotNull(instance,
                          "Singleton instance should never be null on call " + (i + 1));
        }
    }
   
    @Test
    @DisplayName("Singleton memory address should remain constant")
    void testConstantMemoryAddress() {
        // Act: Get instance and check memory address
        DatabaseConnection instance = DatabaseConnection.getInstance();
        int initialAddress = System.identityHashCode(instance);
       
        // Get instance again and check address
        DatabaseConnection sameInstance = DatabaseConnection.getInstance();
        int secondAddress = System.identityHashCode(sameInstance);
       
        // Assert: Memory addresses should be identical
        assertEquals(initialAddress, secondAddress,
                     "Memory address should remain constant for singleton");
    }
   
    @Test
    @DisplayName("Validate singleton pattern implementation")
    void testSingletonPatternValidation() {
        // Act: Create multiple references
        DatabaseConnection ref1 = DatabaseConnection.getInstance();
        DatabaseConnection ref2 = DatabaseConnection.getInstance();
        DatabaseConnection ref3 = DatabaseConnection.getInstance();
       
        // Assert: All references point to same object
        assertTrue(ref1 == ref2 && ref2 == ref3,
                   "All references should point to the same singleton object");
    }
   
    @Test
    @DisplayName("Singleton identity verification using equals")
    void testSingletonIdentityWithEquals() {
        // Act
        DatabaseConnection instanceA = DatabaseConnection.getInstance();
        DatabaseConnection instanceB = DatabaseConnection.getInstance();
       
        // Assert: Should be equal and same
        assertEquals(instanceA, instanceB,
                     "Singleton instances should be equal");
        assertSame(instanceA, instanceB,
                   "Singleton instances should have same identity");
    }
   
    @Test
    @DisplayName("Test singleton uniqueness across multiple retrievals")
    void testSingletonUniqueness() {
        // Act: Store first instance
        DatabaseConnection baseline = DatabaseConnection.getInstance();
       
        // Get 3 more instances
        DatabaseConnection test1 = DatabaseConnection.getInstance();
        DatabaseConnection test2 = DatabaseConnection.getInstance();
        DatabaseConnection test3 = DatabaseConnection.getInstance();
       
        // Assert: All should match baseline
        assertSame(baseline, test1, "Test1 should match baseline");
        assertSame(baseline, test2, "Test2 should match baseline");
        assertSame(baseline, test3, "Test3 should match baseline");
    }
   
    @Test
    @DisplayName("Verify singleton object reference consistency")
    void testObjectReferenceConsistency() {
        // Act: Get two instances
        DatabaseConnection primary = DatabaseConnection.getInstance();
        DatabaseConnection secondary = DatabaseConnection.getInstance();
       
        // Get hash codes
        int primaryHash = primary.hashCode();
        int secondaryHash = secondary.hashCode();
       
        // Assert: Hash codes should match for same object
        assertEquals(primaryHash, secondaryHash,
                     "Hash codes should be identical for singleton instance");
        assertSame(primary, secondary,
                   "Both should reference the exact same object");
    }
}