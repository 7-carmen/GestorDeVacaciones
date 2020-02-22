package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Vacaciones;

@Component
@Transactional
public class VacacionesToStringConverter implements Converter<Vacaciones, String> {
	
	@Override
	public String convert(Vacaciones vacaciones) {
		String result;

		if (vacaciones == null)
			result = null;
		else
			result = String.valueOf(vacaciones.getId());

		return result;
	}

}
