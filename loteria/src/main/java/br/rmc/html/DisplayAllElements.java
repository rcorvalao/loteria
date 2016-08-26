package br.rmc.html;

import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;

/**
 * Demonstrates the ehaviour of the library when retrieving 
 * all elements from a document containing a mix of normal HTML, 
 * different types of server tags, and badly formatted HTML.
 *  
 * @author rcorvalao
 */
public class DisplayAllElements {

	public static void main(String[] args) throws Exception {
		String sourceUrlString = "C:/Temp/MegaSennaResultados/D_MEGA.HTM";
		if (args.length == 0)
			System.err.println("Using default argument of \"" + sourceUrlString
					+ '"');
		else
			sourceUrlString = args[0];
		if (sourceUrlString.indexOf(':') == -1)
			sourceUrlString = "file:" + sourceUrlString;
		MicrosoftTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this
											// example otherwise they override
											// processing instructions
		MasonTagTypes.register();
		Source source = new Source(new URL(sourceUrlString));
		List<Element> elementList = source.getAllElements();
		for (Element element : elementList) {
			System.out
					.println("-------------------------------------------------------------------------------");
			System.out.println(element.getDebugInfo());
			if (element.getAttributes() != null)
				System.out.println("XHTML StartTag:\n"
						+ element.getStartTag().tidy(true));
			System.out.println("Source text with content:\n" + element);
		}
		System.out.println(source.getCacheDebugInfo());
	}

}
