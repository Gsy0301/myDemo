# Java 基本输入输出

这部分知识是关于基本的控制台来实现输入输出。

# Scanner类介绍

先简单的认识一下，若要通过控制台进行输入，首先需要构造一个与**标准输入流**System.in关联的Scanner对象。

```java
Scanner sc = new Scanner(System.in);
```

然后就可以使用Scanner类的各种方法进行读取输入了，例如 **nextLine()**方法将读取一行输入。

```java
String name = sc.nextLine();
```

**Scanner**类位于**java.util**包下，并是一个用**final**修饰的类，实现了**Iterator<String>, Closeable**接口。

此类下一些常见的方法如下：

## 常用方法

|  返回类型  |            方法             |                           方法描述                           |
| :--------: | :-------------------------: | :----------------------------------------------------------: |
|  构造方法  | Scanner(InputStream source) |      构造一个新的 `Scanner` ，产生从指定输入流扫描的值       |
|    void    |           close()           |                         关闭此扫描仪                         |
|  boolean   |          hasNext()          |        如果此扫描仪在其输入中有另一个令牌，则返回true        |
|  boolean   |     hasNextBigDecimal()     | 如果在此扫描器输入信息的下一个标记可以使用 nextBigDecimal()方法解释为BigDecimal，则返回true |
|  boolean   |     hasNextBigInteger()     | 如果在此扫描器输入信息的下一个标记可以使用默认基数 nextBigInteger()方法解释为BigInteger，则返回true |
|  boolean   |      hasNextBoolean()       | 如果此扫描器输入中的下一个标记可以使用从字符串true 或 false创建的不区分大小写的模式解释为布尔值，则返回true |
|  boolean   |        hasNextByte()        | 如果此扫描仪输入中的下一个标记可以使用 nextByte()方法将其 解释为默认基数中的字节值，则返回true |
|  boolean   |       hasNextDouble()       | 如果扫描仪的输入中的下一个标记可以使用 nextDouble()方法将其解释为双重值，则返回true |
|  boolean   |       hasNextFloat()        | 如果扫描仪输入中的下一个标记可以使用 nextFloat()方法将其解释为浮点值，则返回true |
|  boolean   |        hasNextInt()         | 如果此扫描仪输入中的下一个标记可以使用 nextInt()方法解释为默认基数中的int值，则返回true |
|  boolean   |        hatNextLine()        |            如果扫描仪的输入中有另一行，则返回true            |
|  boolean   |        hasNextLong()        | 如果此扫描仪输入中的下一个标记可以使用 nextLong()方法将其 解释为默认基数中的长整型值，则返回true |
|  boolean   |       hasNextShort()        | 如果此扫描仪输入中的下一个标记可以使用 nextShort()方法将其 解释为默认基数中的一个短值，则返回true |
|   String   |           next()            |              查找并返回此扫描仪的下一个完整令牌              |
| BigDecimal |      nextBigDecimal()       |             将输入的下一个标记扫描为`BigDecimal`             |
| BigInteger |      nextBigInteger()       |             将输入的下一个标记扫描为`BigInteger`             |
|  boolean   |        nextBoolean()        |          将输入的下一个标记扫描为布尔值，并返回该值          |
|    byte    |         nextByte()          |               将输入的下一个标记扫描为` byte`                |
|   double   |        nextDouble()         |              将输入的下一个标记扫描为 `double`               |
|   float    |         nextFloat()         |               将输入的下一个标记扫描为 `float`               |
|    int     |          nextInt()          |                将输入的下一个标记扫描为 `int`                |
|   String   |        nextString()         |            将此扫描仪推进到当前行并返回跳过的输入            |
|    long    |         nextLong()          |               将输入的下一个标记扫描为 `long`                |
|   short    |         nextShort()         |               将输入的下一个标记扫描为 `short`               |

## 关于next() 、 nextLine()

next() 不会扫描字符前/后的空格/Tab键，只扫描字符(字符前后不算) ，遇到空格/Tab结束

nextLine() 会扫描字符前/后的空格/Tab键，遇到回车结束

```java
public static void main() {
    Scanner sc = new Scanner(System.in);
    
    // 键盘输入abc def gh
    
    // data1 = "abc"
    String data1 = sc.next();
    
    // data2 = "abc def gh"
    String data2 = sc.nextLine();
}
```



# ACM

不同于核心代码格式(leetcode代码样式)，ACM格式需要自己处理输入输出，尤其是输入。

ACM输入分为两种，单个测试数据输入，和多个测试数据输入。

单个测试数据输入，使用nextXxx()方法获取之后，调用自己的算法程序即可，如下:

```java
public static void main() {
    Scanner sc = new Scanner(System.in);
    
    //先进行检测输入是否含有数据，实际其实可以不用写
    if(!sc.hasNext()) return; 
    
    //先进行读取String数据，再转化为int，boolean等数据(前提可转化)
    // 或者直接使用 int data = sc.nextInt()(前提输入可转为int)
    String data = sc.next(); 
    
    // 调用自己的算法程序    
  	
}
```



多个测试数据输入，一般需使用while(xx.hasNext()) {} 先进行检测有无输入数据，有的话，进行nextxxx()方法进行读取到单个输入数据，然后调用自己的算法程序即可，如下：

```java
public static void main() {
    
    Scanner sc = new Scanner(System.in);
    
    while(sc.hasNext()) {
        String data = sc.next();
        
        // 调用自己的算法程序 
    }
}
```

## 单个数据输入

### 基本数据类型输入

两种方式，使用nextxxx()直接获取对应的数据类型 或 先使用next() nextLine()获得String类型，再转化为对应的数据类型。

方式一：直接获取

```java
public statci void main() {
    Scanner sc = new Scanner(System.in);
    
    //若下次扫描的内容可以通过nextInt()转化为整数，则返回true，进入if
    if (sc.hasNextInt()) int dataInt = sc.nextInt();
    
    //若下次扫描的内容可以通过nextFloat()转化为浮点数，则返回true，进入if
    if (sc.hasNextFloat()) float dataFloat = sc.nextFloat();
    
    
    if (sc.hasNextShort()) short dataShort = sc.nextShort();
    
    if (sc.hasNextLong()) long dataLond = sc.nextLong();
    
    if (sc.hasNextDouble) double dataDouble = sc.nextDouble();
    
    if (sc.hasNextByte()) byte dataByte = sc.nextByte();
    
    // 注意boolean 输入 需要为 true 或者 false ，输入其他都是false
    if (sc.hasNextBoolean()) boolean dataBoolean = sc.nextBoolean();
    
    if (sc.hasNextBigDecimal()) BigDecimal dataBigDecima = sc.nextBigDecima();
    
    if (sc.hasNextBigInteger()) BigInteger dataBigInteger = sc.nextBigInteger();
    
    //char 不同，Scanner本身没有支持获取char类型的数据，但是可以按照如下方式进行获取
    if (sc.hasNext()) char dataChar = sc.next().charAt(0);
    
}
```

方式二：获取再转化

```java
public statci void main() {
    Scanner sc = new Scanner(System.in);
    
    String data = sc.next();
    
    //转化前提：可以被转化
    //比如 "1" -> 1 (int) 可以
    //"a" -> 1 (int) 不可以
    
    int dataInt = Integer.parseInt(data);
    
    float dataFloat = Float.parseFloat(data);
    
    short dataShort = Short.parseShort(data);
    
    Long dataLong = Long.parseLong(data);
    
    boolean dataBoolean = Boolean.parseBoolean(data);
    
    char dataChar = data.charAt(0);
    
    double dataDouble = Double.parseDouble(data);
    
    BigDecimal dataBigDecimal = new BigDecimal(data);
    
    BigInteger dataBigInteger = new BigInteger(data);
}
```

### 数组类型输入

#### 不定长 以空格，逗号等隔开

输入示例如下：

1 2 3 4 5 6 7 ... 

将输入的作为测试数组。

可按照以下程序进行处理：

```java
public static void main() {
    Scanner sc = new Scanner(System.in);

    // 空格隔开--split(" ")
    // 逗号隔开--split(",")
    String[] datas = sc.nextLine().split(" ");
    
    
    int[] data = new int[datas.length];
    
    for(int i = 0; i < datas.length; i++){
        data[i] = Integer.parseInt(datas[i]);
    }

    System.out.println(Arrays.toString(data));
    
    //接下来调用自己的算法程序
    
}
```

#### 定长，给长度

输入示例如下：

5

1 2 3 4 5

第一行为数组长度，将第二行输入的作为测试数组。

可按照以下程序进行处理

```java
public statci void main() {
    Scanner sc = new Scanner(System.in);
    int len = sc.nextInt();
    int[] data = new int[len];
    for (int i = 0; i < len; i++){
        data[i] = sc.nextInt();
    }
    
    System.out.println(Arrays.toString(data));
    
     //接下来调用自己的算法程序
}
```

### 链表类型输入

### 二叉树类型输入

## 多个数据输入

