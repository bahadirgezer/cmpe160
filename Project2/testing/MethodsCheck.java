import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import ports.Port;
import ships.Ship;
import containers.*;
import java.lang.reflect.*;

public class MethodsCheck {
	
	@Test
	void ContainerConsumptionTest() {
		Container basic = new BasicContainer(0, 123);
		assertEquals(2.50*123, basic.consumption(), 0.0000001);
		Container heavy = new HeavyContainer(0, 123);
		assertEquals(3.00*123, heavy.consumption(), 0.0000001);
		Container liq = new LiquidContainer(0, 123);
		assertEquals(4.00*123, liq.consumption(), 0.0000001);
		Container ref = new RefrigeratedContainer(0, 123);
		assertEquals(5.00*123, ref.consumption(), 0.0000001);
	}
	
	@Test
	void ContainerEqualTest() {
		// Type unequality
		Container basic = new BasicContainer(0, 123);
		Container heavy = new HeavyContainer(0, 123);
		Container liq = new LiquidContainer(0, 123);
		Container ref = new RefrigeratedContainer(0, 123);
		
		assertFalse(basic.equals(heavy));
		assertFalse(basic.equals(liq));
		assertFalse(basic.equals(ref));
		assertFalse(heavy.equals(basic));
		assertFalse(heavy.equals(liq));
		assertFalse(heavy.equals(ref));
		assertFalse(liq.equals(basic));
		assertFalse(liq.equals(heavy));
		assertFalse(liq.equals(ref));
		assertFalse(ref.equals(basic));
		assertFalse(ref.equals(heavy));
		assertFalse(ref.equals(liq));
		
		// ID unequality
		Container tmp1, tmp2;
		tmp1 = new BasicContainer(0, 123);
		tmp2 = new BasicContainer(1, 123);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new HeavyContainer(0, 123);
		tmp2 = new HeavyContainer(1, 123);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new LiquidContainer(0, 123);
		tmp2 = new LiquidContainer(1, 123);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new RefrigeratedContainer(0, 123);
		tmp2 = new RefrigeratedContainer(1, 123);
		assertFalse(tmp1.equals(tmp2));
		
		// Weight unequality
		tmp1 = new BasicContainer(0, 0);
		tmp2 = new BasicContainer(0, 1);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new HeavyContainer(0, 0);
		tmp2 = new HeavyContainer(0, 1);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new LiquidContainer(0, 0);
		tmp2 = new LiquidContainer(0, 1);
		assertFalse(tmp1.equals(tmp2));
		tmp1 = new RefrigeratedContainer(0, 0);
		tmp2 = new RefrigeratedContainer(0, 1);
		assertFalse(tmp1.equals(tmp2));
		
		// Equal
		tmp1 = new BasicContainer(123, 123);
		tmp2 = new BasicContainer(123, 123);
		assert(tmp1.equals(tmp2));
		tmp1 = new HeavyContainer(123, 123);
		tmp2 = new HeavyContainer(123, 123);
		assert(tmp1.equals(tmp2));
		tmp1 = new LiquidContainer(123, 123);
		tmp2 = new LiquidContainer(123, 123);
		assert(tmp1.equals(tmp2));
		tmp1 = new RefrigeratedContainer(123, 123);
		tmp2 = new RefrigeratedContainer(123, 123);
		assert(tmp1.equals(tmp2));
		
	}

	@Test
	void loadUnloadContainersTest() throws Exception {
		Port port = new Port(0, 0, 0);
		Ship ship = new Ship(0, port, 100, 10, 10, 5, 5, 10);
		Container c1 = new BasicContainer(0, 10);
		Container c2 = new HeavyContainer(1, 10);
		Container c3 = new LiquidContainer(2, 10);
		Container c4 = new RefrigeratedContainer(3, 10);
		
		// Add containers to the port
	    Class<?> secretClass = port.getClass();
	    Field fields[] = secretClass.getDeclaredFields();
	    for (Field field : fields) {
	        if (field.getName().equals("containers")) {
	        	field.setAccessible(true);
	        	((ArrayList<Container>)field.get(port)).add(c1);
	        	((ArrayList<Container>)field.get(port)).add(c2);
	        	((ArrayList<Container>)field.get(port)).add(c3);
//	        	((ArrayList<Container>)field.get(port)).add(c4);
	       }
	    }
	    
	    ArrayList<Container> should = new ArrayList<>();
	    
	    assert(ship.load(c1));
	    assertFalse(ship.load(c1));
	    
	    should.clear();
	    should.add(c1);
	    assertIterableEquals(ship.getCurrentContainers(), should);
	    
	    assert(ship.unLoad(c1));
	    assertFalse(ship.unLoad(c1));
	    assertFalse(ship.unLoad(c2));
	    assert(ship.load(c2));
	    assert(ship.load(c1));
	    
	    should.clear();
	    should.add(c1);
	    should.add(c2);
	    assertIterableEquals(ship.getCurrentContainers(), should);
	    
	    assertFalse(ship.load(c2));
	    assert(ship.unLoad(c2));
	    assertFalse(ship.unLoad(c2));
	    assertFalse(ship.unLoad(c2));
	    assert(ship.load(c3));
	    assertFalse(ship.load(c3));
	    
	    should.clear();
	    should.add(c1);
	    should.add(c3);
	    assertIterableEquals(ship.getCurrentContainers(), should);
	    
	    
	    assertFalse(ship.load(c4));
	    assertFalse(ship.unLoad(c4));
	    assert(ship.unLoad(c3));
	    
	    should.clear();
	    should.add(c1);
	    assertIterableEquals(ship.getCurrentContainers(), should);
	    
	}

	@Test
	void loadWeightLimitTest() throws Exception {
		Port port = new Port(0, 0, 0);
		Ship ship = new Ship(0, port, 100, 10, 10, 5, 5, 10);
		Container c1 = new BasicContainer(0, 90);
		Container c2 = new HeavyContainer(1, 10);
		Container c3 = new LiquidContainer(2, 100);
		Container c4 = new RefrigeratedContainer(3, 101);
		
		// Add containers to the port
	    Class<?> secretClass = port.getClass();
	    Field fields[] = secretClass.getDeclaredFields();
	    for (Field field : fields) {
	        if (field.getName().equals("containers")) {
	        	field.setAccessible(true);
	        	((ArrayList<Container>)field.get(port)).add(c1);
	        	((ArrayList<Container>)field.get(port)).add(c2);
	        	((ArrayList<Container>)field.get(port)).add(c3);
	        	((ArrayList<Container>)field.get(port)).add(c4);
	       }
	    }
	    
	    assert(ship.load(c1));
	    assert(ship.load(c2));
	    assertFalse(ship.load(c3));
	    assertFalse(ship.load(c4));
	    ship.unLoad(c2);
	    assertFalse(ship.load(c3));
	    assert(ship.load(c2));
	    ship.unLoad(c1);
	    assertFalse(ship.load(c3));
	    ship.unLoad(c2);
	    assert(ship.load(c3));
	    assertFalse(ship.load(c2));
	    assertFalse(ship.load(c4));
	    ship.unLoad(c3);
	    assertFalse(ship.load(c4));
	}
	
	@Test
	void loadContainerLimitTest() throws Exception {
		Port port = new Port(0, 0, 0);
		Ship ship = new Ship(0, port, 100, 2, 10, 5, 5, 10);
		Container c1 = new BasicContainer(0, 10);
		Container c2 = new HeavyContainer(1, 10);
		Container c3 = new LiquidContainer(2, 10);
		Container c4 = new RefrigeratedContainer(3, 10);
		
		// Add containers to the port
	    Class<?> secretClass = port.getClass();
	    Field fields[] = secretClass.getDeclaredFields();
	    for (Field field : fields) {
	        if (field.getName().equals("containers")) {
	        	field.setAccessible(true);
	        	((ArrayList<Container>)field.get(port)).add(c1);
	        	((ArrayList<Container>)field.get(port)).add(c2);
	        	((ArrayList<Container>)field.get(port)).add(c3);
	        	((ArrayList<Container>)field.get(port)).add(c4);
	       }
	    }
	    
	    assert(ship.load(c1));
	    assert(ship.load(c2));
	    assertFalse(ship.load(c3));
	    assertFalse(ship.load(c4));
	    ship.unLoad(c1);
	    assert(ship.load(c3));
	    assertFalse(ship.load(c1));
	    ship.unLoad(c2);
	    assert(ship.load(c4));
	    assertFalse(ship.load(c2));
	}
	
	@Test
	void reFuelTest() throws Exception {
		Port port = new Port(0, 0, 0);
		Ship ship = new Ship(0, port, 100, 10, 10, 5, 5, 10);
		
		ship.reFuel(20.5);
		
		// Check fuel
	    Class<?> secretClass = ship.getClass();
	    Field fields[] = secretClass.getDeclaredFields();
	    for (Field field : fields) {
	        if (field.getName().equals("fuel")) {
	        	field.setAccessible(true);
	        	assertEquals(field.get(ship), 20.5);
	       }
	    }
		
	}
	
	@Test
	void historyCurrentTest() throws Exception {
		Port port1 = new Port(0, 0, 0);
		Port port2 = new Port(1, 0, 0);
		Port uzak = new Port(2, 100000000, 100000000);
		Ship ship1 = new Ship(0, port1, 0, 0, 0, 0, 0, 10000);
		Ship ship2 = new Ship(1, port1, 0, 0, 0, 0, 0, 10000);
		Ship ship3 = new Ship(2, port1, 0, 0, 0, 0, 0, 10000);
		
		assert(check(port1, "current", ship1, ship2, ship3));
		assert(check(port1, "history"));
		assert(check(port2, "current"));
		assert(check(port2, "history"));
		assert(ship1.sailTo(port1));
		assert(check(port1, "current", ship1, ship2, ship3));
		assert(check(port1, "history", ship1));
		assert(check(port2, "current"));
		assert(check(port2, "history"));
		assert(ship1.sailTo(port2));
		assert(check(port1, "current", ship2, ship3));
		assert(check(port1, "history", ship1));
		assert(check(port2, "current", ship1));
		assert(check(port2, "history"));
		assert(ship2.sailTo(port2));
		assert(check(port1, "current", ship3));
		assert(check(port1, "history", ship1, ship2));
		assert(check(port2, "current", ship1, ship2));
		assert(check(port2, "history"));
		assert(ship3.sailTo(port2));
		assert(check(port1, "current"));
		assert(check(port1, "history", ship1, ship2, ship3));
		assert(check(port2, "current", ship1, ship2, ship3));
		assert(check(port2, "history"));
		assert(ship1.sailTo(port1));
		assert(check(port1, "current", ship1));
		assert(check(port1, "history", ship1, ship2, ship3));
		assert(check(port2, "current", ship2, ship3));
		assert(check(port2, "history", ship1));
		assert(ship1.sailTo(port2));
		assert(check(port1, "current"));
		assert(check(port1, "history", ship1, ship2, ship3));
		assert(check(port2, "current", ship1, ship2, ship3));
		assert(check(port2, "history", ship1));
		assertFalse(ship2.sailTo(uzak));
		assert(check(port1, "current"));
		assert(check(port1, "history", ship1, ship2, ship3));
		assert(check(port2, "current", ship1, ship2, ship3));
		assert(check(port2, "history", ship1));
		
	}
	
	boolean check(Port port, String name, Ship... ships) throws Exception {
	    Class<?> secretClass = port.getClass();
	    Field fields[] = secretClass.getDeclaredFields();
	    for (Field field : fields) {
	        if (field.getName().equals(name)) {
	        	field.setAccessible(true);
	        	ArrayList<Ship> list = ((ArrayList<Ship>)field.get(port));
	        	return list.containsAll(Arrays.asList(ships)) && 
	        			list.size() == ships.length;
	       }
	    }
	    assertFalse("This is an unexpected error", true);
		return false;
	}
	
}
