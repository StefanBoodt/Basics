/**
 * A class that does some calls to the
 * <a href="http://www.jfree.org/jfreesvg/index.html">JFreeSVG</a>
 * library. Since that library already does the SVG handling, the classes
 * in this package simply move it to the JFreeSVG package. This means
 * that all this package actually does is clarify and make more easy to
 * to work with the package. If you require anything that is not ordinary
 * you should give the package a look. The referred library is small, so
 * it should be easy to understand.
 * 
 * <p>
 * Since the calls are redirected the outcome would be exactly the same,
 * but this package binds methods that usually follow each other together
 * to make it work with even less method calls. Therefore limiting the
 * amount of work to be done, but also reducing the flexibility in the
 * process. As noted before, it is advised to use the JFreeSVG package if
 * the amount of customization feels to limited, or when you simply enjoy
 * to do the work yourself.
 * </p>
 * 
 * @since 25-8-2014
 * 
 * @author stefanboodt
 *
 */
package svg;