package txeim.Logindemo.Service;

import net.bytebuddy.implementation.MethodCall;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import txeim.Logindemo.Entity.UserEntity;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception{
        return Stream.of(
                Arguments.of(UserEntity.builder().username("ram").password("ram").build()),
                Arguments.of(UserEntity.builder().username("mohan").password("mohan").build())

        );
    }
}
