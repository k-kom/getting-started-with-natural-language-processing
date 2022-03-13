### 1.2.1

query するときのはなし

- マシンは人間と同じ意味で言語は理解できない
- vector で考える
- `query="management meeting"` は `["management" "meeting"]` となる
- それぞれの単語をカウントして `[3, 5]` となったら、 それぞれの単語を軸としたベクトルが図示できる


``` python
“document1 = “meeting ... management ... meeting ... management ... meeting” document1 += “... management ... meeting ... meeting”    #A


vector = [0, 0]    #B

for word in document1.split(" "):    #C
    if word=="management":
        vector[0] = vector[0] + 1    #D
    if word=="meeting":
        vector[1] = vector[1] + 1    #E

print (vector)    #F”

```

- 座標にクエリとドキュメントをプロットしてみてユークリッド距離を取る
  - (4,1) と (3,5) だったら (1,1) との距離は (4,1) の方が近い
- ユークリッド距離は不十分な点があるので cosine similarity を考える
  - cosine similarity はベクトルの近さを測る値で [-1, 1] をとって、 1 が最も近い (cos0)
  - word count の場合は cosPI がありえないので [0,1] をとる
  - 今までの例だと [3,5] のが近いやろ、 という話になるとおもう
- cosine(vec1,vec2) = dot_product(vec1,vec2)/(length(vec1)*length(vec2))
  - dot product は同じ要素?同士を掛けて合計したやつ
