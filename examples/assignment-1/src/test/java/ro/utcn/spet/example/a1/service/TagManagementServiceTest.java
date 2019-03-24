

package ro.utcn.spet.example.a1.service;

        import org.junit.Assert;
        import org.junit.Test;
        import ro.utcn.spet.example.a1.entity.Tag;
        import ro.utcn.spet.example.a1.repository.RepositoryFactory;
        import ro.utcn.spet.example.a1.repository.memory.InMemoryRepositoryFactory;

public class TagManagementServiceTest {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createTagRepository().save(new Tag(1, "A.FN"));
        factory.createTagRepository().save(new Tag(2, "B.FN"));
        return factory;
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        // arrange - create a mocked factory and a service backed up by this factory
        RepositoryFactory factory = createMockedFactory();
        TagManagementService service = new TagManagementService(factory);

        // act - remove a student with a well-known ID
        service.findidtq("B.FN");

        // assert - expect that the student was removed from the repository and the other student is still there
        Assert.assertEquals(2, factory.createTagRepository().findAll().size());

    }

}
