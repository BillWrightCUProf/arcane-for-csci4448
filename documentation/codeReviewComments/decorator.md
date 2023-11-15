# Decorator Implementation 

Do we need the Empty Bag class? Why not get rid of if and make TreasureBag a concrete class? This saves a lot of code.

Then we could eliminate the format method and just implement toString like this:

```java

class TreasureBag {
    public String toString() {
        return "Bag: ";
    }
}

class TreasureBagDecorator() {
    String name;

    String toString() {
        return name + ", " + treasureBag.toString();
    }
}

class Armor {
    Public Armor(TreasureBag treasureBag) {
        super(treasureBag);
        name = "Armor";
    }
}
```

# What is an alternative to the Decorator pattern?

Now how does this compare to NOT using the decorator pattern? Just using a list 
of Treasures instead?

Only need Treasure items — no parallel decorator classes.

Still need a TreasureBag (not essential, but cleaner).

Encapsulates all the information about a treasure: name and values in one class
Don’t need the **new** methods in the _addTreasureToBag()_ method, which tightly couple the Adventurer class 
to all Treasure items and all Treasure Decorators.

Without using Decorators, we don’t need to know about any particular treasure class. 
Zero. Just the Treasure interface (or abstract class)

Also, we can probably eliminate the special code for the potion:

```java
case POTION -> {
    this.treasureBag = new Potion(this.treasureBag);
    this.health += Potion.POTION_EFFECT;
}
```

We could create Potions with custom health effects:

```java
class Potion {
    Integer potionEffect;
    
    Potion(Integer potionEffect) {
        super();
        this.potionEffect = potionEffect;
    }
    
    Integer getHealthBonus() {
        return potionEffect;
    }
}
```

Then on the Adventure class, we don't need to necessarily add it to the 
health instance variable, as we might always wonder if we'd added it already
or not.

But even more importantly, we can't properly answer these questions until we 
see how the health is used. To that we need...tests! TDD, baby!

```java
class Adventurer {
    Integer getHealth() {
        return this.health + treasureBag.getHealthBonus;
    }
}
```
 