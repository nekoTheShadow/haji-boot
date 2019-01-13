[槙俊明『はじめてのSpring Boot: スプリング・フレームワークで簡単Javaアプリ開発』(I・O BOOKS; 工学社)](https://www.amazon.co.jp/%E3%81%AF%E3%81%98%E3%82%81%E3%81%A6%E3%81%AESpring-Boot%E2%80%95%E3%82%B9%E3%83%97%E3%83%AA%E3%83%B3%E3%82%B0%E3%83%BB%E3%83%95%E3%83%AC%E3%83%BC%E3%83%A0%E3%83%AF%E3%83%BC%E3%82%AF%E3%81%A7%E7%B0%A1%E5%8D%98Java%E3%82%A2%E3%83%97%E3%83%AA%E9%96%8B%E7%99%BA-I%E3%83%BB-BOOKS-%E4%BF%8A%E6%98%8E/dp/4777519694/ref=pd_lpo_sbs_14_img_2?_encoding=UTF8&psc=1&refRID=HK9AHQGJ6KJSQ9H598F5)の内容を"写経"したものです。もっとも単純な"写経"ではなく、以下の点を変更しています。

- Javaのバージョンを8から11に変更
    - たとえばローカル変数の型推論など、Java8以降に導入された機能を断りなく利用しています。
- Spring Bootのバージョンについては、本書では1系が採用されているが、ここでは2系を採用。
    - それにともない、依存するライブラリ・jarのバージョンも断りなく変更しています。
- 画面生成に利用するTwitter Bootstrapは3系から4系に変更。
- 作成したアプリケーションのデプロイ先を"Pivotal Cloud Foundry"から"IBM Cloud"に変更。
    - IBM CloudはPivotal Cloud Foundryと同じくCloud Foundryを採用している && 無料枠(Lite Plan)の利用にクレジットカード登録が不要。
    - IBM Cloudへの変更に伴って、利用するDBMSをMy SQLからDb2に変更した。
        - IBM Cloudの無料枠ではMy SQLは使えないため。