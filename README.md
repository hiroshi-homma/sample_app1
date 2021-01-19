### 使用ライブラリ
 - support library
 
 core-ktx
 
 material
 
 appcompat
 
 constraintlayout
 
 recyclerView
 
 swipeRefreshLayout
 
 navigation
 
 lifecycle(livedata-ktx, viewmodel-ktx)
 
 kotlinx-coroutines
 
 - converter
 
 gson
 
 - di
 
 dagger
 
 - db
 
 room
 
 - http client
 
 retrofit
 
 - network
 
 okhttp

### ライブラリ選定理由
 - support library系
 
 →画面構築やロジック部分の実装のため。
 
 - room
 
 → sharedprefernceが非推奨になりそうなので、採用。
 
 - dagger
 
 → google公式のdi用ライブラリのため(koinも人気なので考えたがhiltへの期待も込めて採用)
 
 - gson
 
 → roomへjsonStringを登録するために採用。

 - 同種のライブラリを使用しない理由。
 
 → 同種のライブラリでは、googleの日々更新に対応して、
 柔軟に対応していけると判断できなかったため。

### 実装時間
全体で一週間ほど

### 設計 MultiModule

<img src="http://tk2-246-32569.vs.sakura.ne.jp/multi_module_image2.png"/>

- 設計手法の選定理由
画面単位、通信単位、モデル単位で開発ができるようにしたかったため選定しました。
