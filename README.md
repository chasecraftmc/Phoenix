# Phoenix
Extensive GUI / Inventory API for the Spigot API

### Installation
1. Retrieve the jar from releases or compile the plugin via git and maven.
2. Add the project as a maven dependency or put it in your build path.
3. Start using the API!

You can also use maven:

```
<repositories>
   	<repository>
   		<id>jitpack.io</id>
   		<url>https://jitpack.io</url>
   </repository>
</repositories>

<dependency>
    <groupId>me.blazingtide</groupId>
    <artifactId>Zetsu</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Usage

```java
private Phoenix phoenix

@Override
public void onEnable() {
    phoenix = new Phoenix(this);
}

```

### Example Usage

```java

class ExampleGUI extends GUI {
}

```

```java
public Optional<TickResult> onTick() {
  button[index] = new Button()
}

```

### Contact
**Telegram**: @BlazingTide
**Discord**:  BlazingTide#3817
