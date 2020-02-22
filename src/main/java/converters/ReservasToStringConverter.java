package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reservas;

@Component
@Transactional
public class ReservasToStringConverter implements Converter<Reservas, String> {
	
	@Override
	public String convert(Reservas reservas) {
		String result;

		if (reservas == null)
			result = null;
		else
			result = String.valueOf(reservas.getId());

		return result;
	}

}
