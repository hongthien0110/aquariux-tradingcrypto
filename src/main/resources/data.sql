-- initialize user_id 123456 wallet balance 50,000 USD
INSERT INTO USER (ID, EMAIL, USER_NAME)
VALUES ('123456', 'test@test.com', 'test');
INSERT INTO WALLET (USER_ID, BALANCE, CURRENCY)
VALUES ('123456', 50000, 'USDT');
INSERT INTO WALLET (USER_ID, BALANCE, CURRENCY)
VALUES ('123456', 1000, 'BTC');
INSERT INTO WALLET (USER_ID, BALANCE, CURRENCY)
VALUES ('123456', 1000, 'ETH');