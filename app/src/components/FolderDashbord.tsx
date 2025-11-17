import { ChartContainer, ChartTooltip, ChartTooltipContent, type ChartConfig } from "./ui/chart";
import { Bar, BarChart, CartesianGrid, LabelList, Line, LineChart, XAxis } from "recharts";
import { useQuery } from "@tanstack/react-query";
import { FolderAPI } from "@/api/FolderAPI";

function getKpisFile(id: string) {
  return FolderAPI.getFileKpis(id);
}


function getKpisFileByPeriod(id: string) {
  return FolderAPI.getFileKpisByPeriod(id);
}

interface FolderDashbordProps {
  id: string;
}

export default function FolderDashbord(props: FolderDashbordProps) {
  const fileKpisFetch = useQuery({
    queryKey: ["arquivos", "kpi"],
    queryFn: () => getKpisFile(props.id).then((response) => response.data),
    retry: 1,
  });

  const fileKpisPeriodFetch = useQuery({
    queryKey: ["arquivos", "period-kpi"],
    queryFn: () => getKpisFileByPeriod(props.id).then((response) => response.data),
    retry: 1,
  });

  const chartConfig = {
    quantity: {
      label: "Quantity",
      color: "#535bf2",
    },
  } satisfies ChartConfig;

  return (
    <div className="w-full grid grid-cols-2">
      <div>
        <span className="text-lg">Extensões dos arquivos importados</span>
        <ChartContainer config={chartConfig} className="h-[300px] w-full">
          <BarChart accessibilityLayer data={fileKpisFetch.data}>
            <XAxis dataKey="type" />
            <Bar dataKey="quantity" fill="var(--color-quantity)" radius={4}>
              <LabelList
                fill="white"
                dataKey="quantity"
                position="inside"
                className="text-lg font-medium"
              />
            </Bar>
          </BarChart>
        </ChartContainer>
      </div>
      <div>
        <span className="text-lg">Quantidade de arquivos por periodo</span>
        <ChartContainer className="h-[300px] w-full" config={{}}>
          <LineChart
            accessibilityLayer
            data={fileKpisPeriodFetch.data}
            margin={{
              left: 12,
              right: 12,
            }}
          >
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="type"
            />
            <ChartTooltip
              cursor={true}
              content={<ChartTooltipContent hideLabel />}
            />
            <Line
              dataKey="quantity"
              type="linear"
              stroke="var(--color-quantity)"
              strokeWidth={2}
              dot={false}
            />
          </LineChart>
        </ChartContainer>

      </div>
    </div>
  );
}
