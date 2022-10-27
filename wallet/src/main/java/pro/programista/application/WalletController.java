package pro.programista.application;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.programista.application.controller.RestMvcController;
import pro.programista.domain.entity.Wallet;
import pro.programista.domain.primitives.Result;
import pro.programista.domain.service.WalletService;

@RestController
@RequiredArgsConstructor
@RequestMapping(WalletController.BASE_URL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletController extends RestMvcController {

  public static final String BASE_URL = "/wallet";

  WalletService walletService;

  @GetMapping(ID_PATH)
  public ResponseEntity<Result<Wallet>> getWallet(@PathVariable UUID id) {
    return resolve(walletService.findById(id));
  }

}
