package com.example.crypto_trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CryptoTradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTradingApplication.class, args);
	}

// 	@RestController
// 	@RequestMapping("/users")
// 		public class HelloController {
//     		@Autowired
// 			private UserRepository userRepository;

// 			@Autowired
// 			private WalletRepository walletRepository;

// 			@Autowired
// 			private TransactionsRepository transactionsRepository;

// 			@Autowired
// 			private PricesRepository pricesRepository;


// 			// @GetMapping
// 			// public List<Users> getAllUsers() {
// 			// 	return userRepository.findAll();
// 			// }

// 			// @GetMapping
// 			// public List<Wallets> getAllWallets() {
// 			// 	return walletRepository.findAll();
// 			// }
// }

}
