package glazer.morsecode;

import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorse() {
		MorseCode morse=new MorseCode();
	
		Assert.assertEquals(".... . .-.. .-.. --- --..-- / -- -.-- / -. .- -- . / .. ... / ... .... .. ..-. .-. .- .-.-.-",morse.toMorseCode("HELLO, MY NAME IS SHIFRA."));
		Assert.assertEquals("- --- -.. .- -.-- --..-- / .. ... / -. .. -.-. . .-.-.-",morse.toMorseCode("Today, is nice."));
		Assert.assertEquals("- .... .-. . . / -... .-.. .. -. -.. / -- .. -.-. .",morse.toMorseCode("Three blind mice"));
	}
	@Test
	public void testToPlainText(){
		MorseCode morse=new MorseCode();
		Assert.assertEquals("TODAY, IS NICE.",morse.toPlainText("- --- -.. .- -.-- --..--  / .. ...  / -. .. -.-. . .-.-.-").toUpperCase());
		Assert.assertEquals("MORSE CODE IS SO MUCH FUN.",morse.toPlainText("-- --- .-. ... . / -.-. --- -.. . / .. ... / ... --- / -- ..- -.-. .... / ..-. ..- -. .-.-.-").toUpperCase());
	}

}
