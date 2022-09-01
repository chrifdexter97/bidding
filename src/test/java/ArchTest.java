import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

//architecture test.
public class ArchTest {

    @Test
    public void serviceShouldNotDependOnPresentation() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("org.bid");

        noClasses()
                .that()
                .resideInAnyPackage("org.bid.service..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("org.bid.presentation..")
                .because("Services and repositories should not depend on web layer")
                .check(importedClasses);
    }
}
