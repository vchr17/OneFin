
import com.example.onefin.data.database.MainDao
import com.example.onefin.data.database.MainDb
import com.example.onefin.data.repository.ApiImpl
import com.example.onefin.data.repository.MainRepositoryImpl
import com.example.onefin.domain.repository.MainRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<Retrofit> {
        ApiImpl().init()
    }

    single<MainDb> {
        MainDb.getDb(context = get())
    }

    single<MainRepository>{
        MainRepositoryImpl(db = get())
    }

}