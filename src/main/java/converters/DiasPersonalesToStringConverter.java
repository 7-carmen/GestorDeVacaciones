package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.DiasPersonales;

@Component
@Transactional
public class DiasPersonalesToStringConverter implements Converter<DiasPersonales, String> {
	
	@Override
	public String convert(DiasPersonales diasPersonales) {
		String result;

		if (diasPersonales == null)
			result = null;
		else
			result = String.valueOf(diasPersonales.getId());

		return result;
	}

}
