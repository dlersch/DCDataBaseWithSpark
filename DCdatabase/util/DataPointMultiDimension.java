/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package database.util;

public class DataPointMultiDimension {
	private int superLayer;
	private int sector;
	private int wire;
	private int layer;
	private int value;

	public DataPointMultiDimension(int superLayer, int sector, int wire, int layer) {
		this.superLayer = superLayer;
		this.sector = sector;
		this.wire = wire;
		this.layer = layer;
	}

	public int getSuperLayer() {
		return superLayer;
	}

	public void setSuperLayer(int superLayer) {
		this.superLayer = superLayer;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public int getWire() {
		return wire;
	}

	public void setWire(int wire) {
		this.wire = wire;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void increment() {
		value++;
	}

}
